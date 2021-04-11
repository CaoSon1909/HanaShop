/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.daos.TblFoodCategoryDAO;
import sonpc.daos.TblFoodDAO;
import sonpc.dtos.TblFoodCategoryDTO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblUserDTO;

/**
 *
 * @author ACER
 */
public class SearchByPriceRangeServlet extends HttpServlet {

    private final String SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER = "customerSearch.jsp";
    private final String SEARCH_DYNAMIC_PAGE_FOR_ADMIN = "adminSearch.jsp";
    private final String MAIN_SEARCH_SERVLET = "MainSearchServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
        boolean catchErr = false;
        try {
            String minString = request.getParameter("txtMinPrice");
            String maxString = request.getParameter("txtMaxPrice");

            if (minString.length() == 0) {
                request.setAttribute("MIN_ERR", "Min value không được null");
                catchErr = true;
            }
            if (maxString.length() == 0) {
                request.setAttribute("MAX_ERR", "Max value không được null");
                catchErr = true;
            }
            if (catchErr) {
                //kiểm tra xem session của ai để gửi msg error đi
                HttpSession session = request.getSession(false);
                if (session != null) {
                    TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (user != null) {
                        int roleID = user.getRoleID();
                        if (roleID == 1) {
                            url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
                        }
                        if (roleID == 2) {
                            url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                        }
                    } else {
                        //user chưa đăng nhập
                        url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                    }
                }
                // cập nhật giao diện
                //load food list
                TblFoodDAO foodDAO = new TblFoodDAO();
                List<TblFoodDTO> listFood = foodDAO.getAllActivatedFood();
                if (listFood != null) {
                    request.setAttribute("FOOD_LIST", listFood);
                }
                //load list category
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (categoryList != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
            } else {
                //ko có lỗi format
                //parsing
                float minFloat = Float.parseFloat(minString);
                float maxFloat = Float.parseFloat(maxString);
                if (minFloat <= 0 || maxFloat <= 0 || minFloat > maxFloat) {
                    request.setAttribute("POSTIVE_ERR", "Min value và max value bắt buộc phải là số dương và min value phải nhỏ hơn max value");
                } else {
                    // ko có lỗi logic
                    //result list
                    TblFoodDAO foodDAO = new TblFoodDAO();
                    List<TblFoodDTO> list = foodDAO.searchFoodByPriceRange(minFloat, maxFloat);
                    if (list != null) {
                        request.setAttribute("FOOD_LIST", list);
                    }

                    //load list category
                    TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                    List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                    if (categoryList != null) {
                        request.setAttribute("CATEGORY_LIST", categoryList);
                    }

                    //session: jsp tự động tạo session
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        //lấy dto từ session lên
                        TblUserDTO dto = (TblUserDTO) session.getAttribute("USER_DTO");
                        if (dto != null) {
                            //user đã đăng nhập
                            //sau đó lấy roleID để kiểm tra
                            int roleID = dto.getRoleID();
                            if (roleID == 1) {
                                //admin
                                url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
                            }
                            if (roleID == 2) {
                                //customer
                                url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                            }
                        } else {
                            //user chưa đăng nhập
                            url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                        }
                    }
                }
            }
            //nếu max min invalid
        } catch (NumberFormatException ex) {
            try {
                //error message
                request.setAttribute("WRONG_FORMAT_EXCEPTION", "Max-Min sai format, vui lòng kiểm tra lại");
                //kiểm tra xem session của ai để gửi msg error đi
                HttpSession session = request.getSession(false);
                if (session != null) {
                    TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (user != null) {
                        int roleID = user.getRoleID();
                        if (roleID == 1) {
                            url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
                        }
                        if (roleID == 2) {
                            url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                        }
                    } else {
                        //user chưa đăng nhập
                        url = SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER;
                    }
                }
                // cập nhật giao diện
                //load food list
                TblFoodDAO foodDAO = new TblFoodDAO();
                List<TblFoodDTO> listFood = foodDAO.getAllActivatedFood();
                if (listFood != null) {
                    request.setAttribute("FOOD_LIST", listFood);
                }
                //load list category
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (categoryList != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
            } catch (NamingException | SQLException ex1) {
                log(ex.getMessage());
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

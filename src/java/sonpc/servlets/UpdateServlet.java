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
import sonpc.daos.TblFoodCategoryDAO;
import sonpc.daos.TblFoodDAO;
import sonpc.dtos.TblFoodCategoryDTO;
import sonpc.dtos.TblFoodDTO;

/**
 *
 * @author ACER
 */
public class UpdateServlet extends HttpServlet {

    private final String SEARCH_DYNAMIC_PAGE_FOR_ADMIN = "adminSearch.jsp";

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
        String url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
        boolean catchErr = false;
        try {
            float foodPrice = 0;
            int foodQuantity = 0;
            int categoryID = 0;
            String foodID = request.getParameter("txtFoodID");
            String foodName = request.getParameter("txtFoodName");
            String foodDescription = request.getParameter("txtFoodDescription");
            String foodPriceString = request.getParameter("txtFoodPrice");
            String foodQuantityString = request.getParameter("txtCurrentQuantity");
            String categoryIDString = request.getParameter("categoryID");

            if (foodName.length() == 0) {
                request.setAttribute("FOOD_NAME_ERR", "Food name cannot be null");
                catchErr = true;
            }
            if (foodDescription.length() == 0) {
                request.setAttribute("FOOD_DES_ERR", "Food description cannot be null");
                catchErr = true;
            }
            if (foodPriceString.length() == 0) {
                request.setAttribute("FOOD_PRICE_ERR", "Food price cannot be null");
                catchErr = true;
            }
            if (foodQuantityString.length() == 0) {
                request.setAttribute("FOOD_QUANTITY_ERR", "Food Quantity cannot be null");
                catchErr = true;
            }
            if (catchErr) {
                //có lỗi => update lại giao diện
                //food list
                TblFoodDAO foodDAO = new TblFoodDAO();
                List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                if (list != null) {
                    request.setAttribute("FOOD_LIST", list);
                }
                //category list
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (list != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
                url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
            } else {
                foodPrice = Float.parseFloat(foodPriceString);
                foodQuantity = Integer.parseInt(foodQuantityString);
                categoryID = Integer.parseInt(categoryIDString);

                if (foodPrice > 0 && foodQuantity > 0) {
                    TblFoodDAO foodDAO = new TblFoodDAO();
                    boolean result = foodDAO.updateFood(foodID, foodName, foodDescription, foodPrice, foodQuantity, categoryID);
                    if (result) {
                        //update thành công, cập nhật giao diện
                        //food list
                        List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                        if (list != null) {
                            request.setAttribute("FOOD_LIST", list);
                        }
                        //category list
                        TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                        List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                        if (list != null) {
                            request.setAttribute("CATEGORY_LIST", categoryList);
                        }
                    } else {
                        //update thất bại, cập nhật giao diện + gửi msg lỗi
                        //food list
                        List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                        if (list != null) {
                            request.setAttribute("FOOD_LIST", list);
                        }
                        //category list
                        TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                        List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                        if (list != null) {
                            request.setAttribute("CATEGORY_LIST", categoryList);
                        }
                        //gửi msg lỗi
                        request.setAttribute("UPDATE_ERR", "Update thất bại. Vui lòng thử lại sau");
                    }
                } else {
                    request.setAttribute("POSITIVE_CONSTRAINT", "Vui lòng kiểm tra lại và đảm bảo các giá trị không âm");
                }

            }

        } catch (NumberFormatException ex) {
            try {
                //nhập price hay quantity tầm bậy
                request.setAttribute("WRONG_FORMAT_EXCEPTION", "Sai format food price hoặc food quantity");
                url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
                //cập nhật lại giao diện
                //food list
                TblFoodDAO foodDAO = new TblFoodDAO();
                List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                if (list != null) {
                    request.setAttribute("FOOD_LIST", list);
                }
                //category list
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (list != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
            } catch (NamingException ex1) {
                log(ex1.getMessage());
            } catch (SQLException ex1) {
                log(ex1.getMessage());
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

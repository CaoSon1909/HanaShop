/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.daos.TblCartDetailDAO;
import sonpc.daos.TblFoodCategoryDAO;
import sonpc.daos.TblFoodDAO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblUserDTO;

/**
 *
 * @author ACER
 */
public class AddToCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String CUSTOMER_SEARCH_PAGE = "customerSearch.jsp";

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
        String url = CUSTOMER_SEARCH_PAGE;
        try {
            //kiểm tra xem user có phải là customer ko, nếu là authenticated customer mới cho add to cart
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                if (user == null) {
                    //user chưa đăng nhập => sendRedirect user về trang login
                    url = LOGIN_PAGE;
                    response.sendRedirect(url);
                } else {
                    //user đã đăng nhập
                    String userEmail = user.getEmail();
                    String foodID = request.getParameter("txtFoodID");
                    //
                    TblCartDetailDAO dao = new TblCartDetailDAO();
                    boolean existed = dao.checkExistedFoodInCart(userEmail, foodID);
                    if (!existed) {
                        //add mới food vào cart, mặc định quantity = 1
                        int quantity = 1;

                        TblFoodDAO foodDAO = new TblFoodDAO();
                        TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(foodID);
                        if (foodDTO != null) {
                            boolean result = dao.addToCartFirstTime(userEmail, foodID, quantity, foodDTO.getPrice());
                            if (result) {
                                //add to cart thành công, update lại giao diện
                                //food list
                                request.setAttribute("FOOD_LIST", foodDAO.getAllActivatedFood());
                                //food category
                                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                                request.setAttribute("CATEGORY_LIST", categoryDAO.loadFoodCategory());
                            }
                        }
                    } else {
                        //update food quantity trong cart
                        //phải lấy được quantity hiện tại của food đó từ DB
                        int quantity = dao.getCurrentQuantity(userEmail, foodID);
                        boolean result = dao.addToCartLater(userEmail, foodID, ++quantity);
                        if (result) {
                            //add to cart thành công, update lại giao diện
                            //food list
                            TblFoodDAO foodDAO = new TblFoodDAO();
                            request.setAttribute("FOOD_LIST", foodDAO.getAllActivatedFood());
                            //food category
                            TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                            request.setAttribute("CATEGORY_LIST", categoryDAO.loadFoodCategory());
                        } else {
                            //add to cart thất bại, gửi thông báo lỗi + cập nhật giao diện
                            
                            //cập nhật giao diện
                            TblFoodDAO foodDAO = new TblFoodDAO();
                            request.setAttribute("FOOD_LIST", foodDAO.getAllActivatedFood());
                            //food category
                            TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                            request.setAttribute("CATEGORY_LIST", categoryDAO.loadFoodCategory());
                            //gửi msg lỗi
                            request.setAttribute("ADD_TO_CART_ERR", "Add to cart thất bại. Vui lòng thử lại sau");
                        }
                    }
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }

        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
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

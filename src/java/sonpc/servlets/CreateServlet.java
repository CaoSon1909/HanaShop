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
public class CreateServlet extends HttpServlet {

    private final String SEARCH_DYNAMIC_PAGE_FOR_ADMIN = "adminSearch.jsp";
    private final String CREATE_PAGE = "create.jsp";

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
        boolean catchNullErr = false;
        boolean catchNegative = false;
        try {
            String foodName = request.getParameter("txtFoodName");
            float foodPrice = 0;
            int foodQuantity = 0;
            if (foodName.length() == 0 || foodName.length() > 30) {
                request.setAttribute("FOOD_NAME_ERR", "Food Name have length From 1 to 30");
                catchNullErr = true;
            }
            String foodDescription = request.getParameter("txtFoodDescription");
            if (foodDescription.length() == 0) {
                request.setAttribute("FOOD_DES_ERR", "Food Description cannot be null");
                catchNullErr = true;
            }
            String foodQuantityString = request.getParameter("txtFoodQuantity");
            if (foodQuantityString.length() == 0) {
                request.setAttribute("FOOD_QUANTITY_ERR", "Food Quantity cannot be null");
                catchNullErr = true;
            } else {
                foodQuantity = Integer.parseInt(foodQuantityString);
                if (foodQuantity <= 0) {
                    request.setAttribute("FOOD_QUANTITY_ERR", "Food Quantity must be greater than 0");
                    catchNegative = true;
                }
            }
            String foodPriceString = request.getParameter("txtFoodPrice");
            if (foodPriceString.length() == 0) {
                request.setAttribute("FOOD_PRICE_ERR", "Food Price cannot be null");
                catchNullErr = true;
            } else {
                foodPrice = Float.parseFloat(foodPriceString);
                if (foodPrice <= 0) {
                    request.setAttribute("FOOD_PRICE_ERR", "Food Price must be greater than 0");
                    catchNegative = true;
                }
            }
            String foodCategoryString = request.getParameter("categoryID");
            if (foodCategoryString.length() == 0) {
                request.setAttribute("FOOD_CATEGORY_ERR", "Food cannot be null");
                catchNullErr = true;
            }
            //parse
            if (catchNullErr || catchNegative) {
                url = CREATE_PAGE;
            } else {
                int foodCategory = Integer.parseInt(foodCategoryString);

                //dao
                TblFoodDAO foodDAO = new TblFoodDAO();
                boolean result = foodDAO.createFood(foodName, foodDescription, foodPrice, foodQuantity, foodCategory);

                if (result) {
                    //create thành công, cập nhật lại giao diện
                    //food list
                    List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                    if (list != null) {
                        request.setAttribute("FOOD_LIST", list);
                    }
                    //food category
                    TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                    List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                    if (categoryList != null) {
                        request.setAttribute("CATEGORY_LIST", categoryList);
                    }
                } else {
                    //create thất bại, cập nhật giao diện + gửi msg lỗi

                    //cập nhật giao diện
                    //food list
                    List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                    if (list != null) {
                        request.setAttribute("FOOD_LIST", list);
                    }
                    //food category
                    TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                    List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                    if (categoryList != null) {
                        request.setAttribute("CATEGORY_LIST", categoryList);
                    }
                    //gửi msg lỗi
                    request.setAttribute("CREATE_ERR", "Create Food thất bại. Vui lòng thử lại sau");
                    url = CREATE_PAGE;
                }
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("NUMBER_FORMAT", "Sai format food price hoặc food quantity");
            url = CREATE_PAGE;
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

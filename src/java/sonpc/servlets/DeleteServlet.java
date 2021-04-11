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
public class DeleteServlet extends HttpServlet {

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
        try {
            String foodID = request.getParameter("txtFoodID");
            //dao
            TblFoodDAO foodDAO = new TblFoodDAO();
            boolean result = foodDAO.deleteFood(foodID);
            if (result) {
                //delete food thành công, update lại giao diện
                //food list
                List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                request.setAttribute("FOOD_LIST", list);
                //food category list
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (categoryList != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
            } else {
                //delete thất bại, gửi msg lỗi đi + cập nhật giao diện
                request.setAttribute("DELETE_ERR", "Delete thất bại. Vui lòng thử lại sau");
                List<TblFoodDTO> list = foodDAO.getAllActivatedFood();
                request.setAttribute("FOOD_LIST", list);
                //food category list
                TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                if (categoryList != null) {
                    request.setAttribute("CATEGORY_LIST", categoryList);
                }
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

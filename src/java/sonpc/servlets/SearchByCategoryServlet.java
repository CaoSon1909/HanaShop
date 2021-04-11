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
public class SearchByCategoryServlet extends HttpServlet {

    private final String SEARCH_DYNAMIC_PAGE_CUSTOMER = "customerSearch.jsp";
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
        String url = SEARCH_DYNAMIC_PAGE_CUSTOMER;
        try {
            String searchValue = request.getParameter("categoryID");
            TblFoodDAO dao = new TblFoodDAO();
            int categoryID = Integer.parseInt(searchValue);
            //load list from DB
            List<TblFoodDTO> list = dao.searchFoodByCategoryID(categoryID);
            if (list != null) {
                request.setAttribute("FOOD_LIST", list);
            }
            //load category from DB
            TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
            List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
            if (categoryList != null) {
                request.setAttribute("CATEGORY_LIST", categoryList);
            }
            //session: trang jsp tự động tạo session (session của pageContext), mà trang mặc định ban đầu là trang jsp => session ko bao giờ null
            HttpSession session = request.getSession(false);
            TblUserDTO dto = (TblUserDTO) session.getAttribute("USER_DTO");
            if (dto != null) {
                //user have logined
                int roleID = dto.getRoleID();
                if (roleID == 1) {
                    url = SEARCH_DYNAMIC_PAGE_FOR_ADMIN;
                }
                if (roleID == 2) {
                    url = SEARCH_DYNAMIC_PAGE_CUSTOMER;
                }
            } else {
                //user not login
                url = SEARCH_DYNAMIC_PAGE_CUSTOMER;
            }
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.dtos.TblUserDTO;

/**
 *
 * @author ACER
 */
public class MainSearchServlet extends HttpServlet {

    //customer
    private final String SEARCH_BY_NAME_SERVLET = "SearchByFoodNameServlet";
    private final String SEARCH_BY_PRICE_RANGE_SERVLET = "SearchByPriceRangeServlet";
    private final String SEARCH_BY_CATEGORY_SERVLET = "SearchByCategoryServlet";
    private final String SEARCH_BY_FOODNAME_IN_HISTORY_SERVLET = "SearchByFoodNameInHistoryServlet";
    private final String CUSTOMER_SEARCH_PAGE = "customerSearch.jsp";
    private final String ADMIN_SEARCH_PAGE = "adminSearch.jsp";

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
        String url = "";
        String msg = (String) request.getAttribute("WRONG_FORMAT_EXCEPTION");
        if (msg != null) {
            //nếu như có lỗi number format exception, quăng lỗi cho trang jsp
            
            //lấy session để kt xem là trang search của ai
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                if (user != null) {
                    int roleID = user.getRoleID();
                    if (roleID == 1) {
                        url = ADMIN_SEARCH_PAGE;
                    }
                    if (roleID == 2) {
                        url = CUSTOMER_SEARCH_PAGE;
                    }
                }
            }
        } else {
            String searchType = request.getParameter("txtSearchType");
            
            if (searchType != null) {
                
                if (searchType.equals("searchByName")) {
                    url = SEARCH_BY_NAME_SERVLET;
                }
                if (searchType.equals("searchByRange")) {
                    url = SEARCH_BY_PRICE_RANGE_SERVLET;
                }
                if (searchType.equals("searchByCategory")) {
                    url = SEARCH_BY_CATEGORY_SERVLET;
                }
                if (searchType.equals("searchNameInHistory")) {
                    url = SEARCH_BY_FOODNAME_IN_HISTORY_SERVLET;
                }
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
        out.close();
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

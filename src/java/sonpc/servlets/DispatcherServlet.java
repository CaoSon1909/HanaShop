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

/**
 *
 * @author ACER
 */
public class DispatcherServlet extends HttpServlet {

    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGIN_DYNAMIC_PAGE = "login.jsp";
    private final String LOG_OUT_SERVLET = "LogoutServlet";
    private final String MAIN_SERVLET = "MainServlet";
    private final String MAIN_SEARCH_SERVLET = "MainSearchServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String CREATE_PAGE = "create.jsp";
    private final String CREATE_SERVLET = "CreateServlet";
    private final String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private final String SHOW_CART_SERVLET = "ShowCartServlet";
    private final String REMOVE_FOOD_FROM_CART_SERVLET = "RemoveFoodFromCartServlet";
    private final String CHECKOUT_SERVLET = "CheckOutServlet";
    private final String CREATE_ORDER_SERVLET = "CreateOrderServlet";
    private final String UPDATE_QUANTITY_SERVLET = "UpdateQuantityServlet";
    private final String SHOW_HISTORY_SERVLET = "ShowHistoryServlet";
    private final String DETAIL_SERVLET = "DetailServlet";

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
        response.setHeader("Cache-Control", "no-cache, no-store");
        PrintWriter out = response.getWriter();
        String button = request.getParameter("btAction");
        String url = MAIN_SERVLET;
        try {
            if ("".equals(button)) {

            } else if ("Login".equals(button)) {
                url = LOGIN_SERVLET;
            } else if ("Login Page".equals(button)) {
                url = LOGIN_DYNAMIC_PAGE;
            } else if ("Log Out".equals(button)) {
                url = LOG_OUT_SERVLET;
            } else if ("Search".equals(button)) {
                url = MAIN_SEARCH_SERVLET;
            } else if ("Update".equals(button)) {
                url = UPDATE_SERVLET;
            } else if ("Delete".equals(button)) {
                url = DELETE_SERVLET;
            } else if ("Create Page".equals(button)) {
                url = CREATE_PAGE;
            } else if ("Create".equals(button)) {
                url = CREATE_SERVLET;
            } else if ("Add To Cart".equals(button)) {
                url = ADD_TO_CART_SERVLET;
            }else if ("Show Cart".equals(button)){
                url = SHOW_CART_SERVLET;
            }else if ("Remove".equals(button)){
                url = REMOVE_FOOD_FROM_CART_SERVLET;
            }else if ("Checkout".equals(button)){
                url = CHECKOUT_SERVLET;
            }else if ("Update Quantity".equals(button)){
                url = UPDATE_QUANTITY_SERVLET;
            }else if ("Proceed to checkout".equals(button)){
                url = CREATE_ORDER_SERVLET;
            }else if ("Show History".equals(button)){
                url = SHOW_HISTORY_SERVLET;
            }else if ("Show Detail".equals(button)){
                url =  DETAIL_SERVLET;
            }
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

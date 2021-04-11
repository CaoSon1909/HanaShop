/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonpc.daos.TblOrderDAO;
import sonpc.dtos.TblOrderDTO;
import sonpc.dtos.TblUserDTO;

/**
 *
 * @author ACER
 */
public class CreateOrderServlet extends HttpServlet {

    private final String FILL_ORDER_SERVLET = "FillOrderServlet";
    private final String CHECK_OUT_SERVLET = "CheckOutServlet";

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
        try {
            //get parameters
            String address = request.getParameter("txtAddress");
            //validate
            if (address == null) {
                request.setAttribute("ADDRESS_ERR", "Address cannot be null");
                url = CHECK_OUT_SERVLET;
            } else {
                String subTotalString = request.getParameter("txtTotal");
                float subTotal = Float.parseFloat(subTotalString);
                String paymentTypeString = request.getParameter("paymentTypeID"); //1 or 2
                int paymentType = Integer.parseInt(paymentTypeString);
                ///date
                Date date = new Date();
                long orderDate = date.getTime();
                //userEmail
                String userEmail = null;
                HttpSession session = request.getSession(false);
                if (session != null) {
                    TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                    if (user != null) {
                        userEmail = user.getEmail();
                    }
                }
                //1: create Order (tạo hóa đơn)
                TblOrderDAO orderDAO = new TblOrderDAO();
                boolean createOrderResult = orderDAO.createOrder(address, subTotal, orderDate, userEmail, paymentType);
                if (createOrderResult) {
                    //tạo hóa đơn thành công, lấy hóa đơn vừa tạo ra rồi đưa vào reqScope
                    TblOrderDTO order = orderDAO.getOrderByEmailAndOrderDate(userEmail, orderDate);
                    request.setAttribute("ORDER", order);
                    url = FILL_ORDER_SERVLET;
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

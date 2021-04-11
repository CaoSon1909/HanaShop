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
import sonpc.daos.TblFoodDAO;
import sonpc.daos.TblOrderDAO;
import sonpc.daos.tblOrderDetailDAO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblOrderDTO;

/**
 *
 * @author ACER
 */
public class SearchByFoodNameInHistoryServlet extends HttpServlet {

    private final String HISTORY_PAGE = "history.jsp";

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
        String url = HISTORY_PAGE;
        try {

            //user search food name => từ bảng tblFood lấy đc foodID 
            //=> lấy ra những order detail nào có foodID đó => lấy ra order có orderID tương ứng
            //1:lấy food ID
            String foodName = request.getParameter("txtFoodName");
            TblFoodDAO foodDAO = new TblFoodDAO();
            List<TblFoodDTO> foodList = foodDAO.searchFoodByName(foodName);
            for (TblFoodDTO dto : foodList) {
                String foodID = dto.getId(); //lấy đc foodID
                //2: lấy orderID(distinct) trong orderDetail
                tblOrderDetailDAO orderDetailDAO = new tblOrderDetailDAO();
                List<String> orderIDList = orderDetailDAO.getDistinctOrderIDList(foodID);
                if (orderIDList != null) {
                    //get order by orderID
                    for (String orderID : orderIDList) {
                        TblOrderDAO orderDAO = new TblOrderDAO();
                        List<TblOrderDTO> orderList = orderDAO.getOrderListCustomerBuy(orderID);
                        if (orderList != null) {
                            request.setAttribute("ORDER_LIST", orderList);
                        }
                    }
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

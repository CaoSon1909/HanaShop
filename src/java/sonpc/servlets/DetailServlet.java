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
import sonpc.dtos.tblOrderDetailDTO;

/**
 *
 * @author ACER
 */
public class DetailServlet extends HttpServlet {

    private final String DETAIL_PAGE = "detail.jsp";

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
            //lấy order lên
            String orderID = request.getParameter("txtCartID");
            TblOrderDAO orderDAO = new TblOrderDAO();
            TblOrderDTO orderDTO = orderDAO.getOrderByOrderID(orderID);

            if (orderDTO != null) {
                request.setAttribute("ORDER_DTO", orderDTO);
            }

            //lấy order detail dụa vào orderID
            tblOrderDetailDAO orderDetailDAO = new tblOrderDetailDAO();
            List<tblOrderDetailDTO> orderDetailList = orderDetailDAO.getOrderDetailByOrderID(orderID);

            for (tblOrderDetailDTO dto : orderDetailList) {
                String foodID = dto.getFoodID();
                TblFoodDAO foodDAO = new TblFoodDAO();
                TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(foodID);
                String foodName = foodDTO.getName();
                dto.setFoodID(foodName);
            }
            request.setAttribute("ORDER_DETAIL_LIST", orderDetailList);
            url = DETAIL_PAGE;
            //
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

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
import sonpc.daos.TblCartDetailDAO;
import sonpc.daos.TblFoodDAO;
import sonpc.dtos.TblCartDetailDTO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblUserDTO;

/**
 *
 * @author ACER
 */
public class CheckOutServlet extends HttpServlet {

    private final String CONFIRM_PAGE = "confirm.jsp";
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
        String url = CONFIRM_PAGE;
        try {
            int quantitySum = 0;
            float subTotal = 0;
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                if (user != null) {
                    String userEmail = user.getEmail();
                    TblCartDetailDAO cartDAO = new TblCartDetailDAO();
                    List<TblCartDetailDTO> list = cartDAO.showCustomerCart(userEmail);
                    for (TblCartDetailDTO dto : list){
                        //chuyển foodID thành foodName để display
                        TblFoodDAO foodDAO = new TblFoodDAO();
                        TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(dto.getFoodID());
                        dto.setFoodID(foodDTO.getName());
                        //get total
                        int quantity = dto.getQuantity();
                        float price = dto.getPrice();
                        float total = price * quantity; 
                        //calculate subtotal + quantitySum
                        quantitySum = quantitySum + quantity;
                        subTotal = subTotal + total;
                    }
                    request.setAttribute("FOOD_IN_CART", list);
                    request.setAttribute("SUB_TOTAL", subTotal);
                    request.setAttribute("QUANTITY_SUM", quantitySum);
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

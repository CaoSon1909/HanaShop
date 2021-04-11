/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonpc.daos.TblCartDetailDAO;
import sonpc.daos.TblFoodDAO;
import sonpc.dtos.TblFoodDTO;

/**
 *
 * @author ACER
 */
public class UpdateQuantityServlet extends HttpServlet {

    private final String SHOW_CART_SERVLET = "ShowCartServlet";

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
        String url = "";
        try {
            String cartID = request.getParameter("txtCartID");
            String quantityString = request.getParameter("txtQuantity");
            //parse
            int quantity = Integer.parseInt(quantityString);
            String foodName = request.getParameter("txtFoodName");
            TblFoodDAO foodDAO = new TblFoodDAO();
            List<TblFoodDTO> foodList = foodDAO.searchFoodByName(foodName);
            if (foodList != null) {
                for (TblFoodDTO dto : foodList) {
                    String foodID = dto.getId();
                    TblCartDetailDAO cartDAO = new TblCartDetailDAO();
                    boolean result = cartDAO.updateQuantity(cartID, quantity, foodID);
                    if (!result) {
                        //update thất bại (do quantity >= currentQuantity trong tblFood, cố tình nhập quantity >= currentQuan)
                        //nếu chỉ gửi đi mỗi err thôi sẽ ko biết là của food nào => thành ra tất cả đều out of stock
                        //lưu Map<foodName, err>
                        String err = "Out of Stock!";
                        HashMap<String, String> mapErr = new HashMap<>();
                        mapErr.put(dto.getName(), err);
                        request.setAttribute("CANNOT_UPDATE_QUANTITY", mapErr);
                        url = SHOW_CART_SERVLET;
                    }
                    if (result) {
                        //update thành công, forward sang ShowCartServlet để display lại cart
                        url = SHOW_CART_SERVLET;
                    }
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        }
        catch (NumberFormatException ex){
            request.setAttribute("NUMBER_FORMAT_EXCEPTION", "Nhập quantity sai format");
            url = SHOW_CART_SERVLET;
        }
        finally {
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

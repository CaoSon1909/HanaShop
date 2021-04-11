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
public class ShowCartServlet extends HttpServlet {

    private final String CART_PAGE = "cart.jsp";

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
        String url = CART_PAGE;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblUserDTO userDTO = (TblUserDTO) session.getAttribute("USER_DTO");
                if (userDTO != null) {
                    //foods in cart
                    TblCartDetailDAO dao = new TblCartDetailDAO();
                    TblFoodDAO foodDAO = new TblFoodDAO();
                    List<TblCartDetailDTO> list = dao.showCustomerCart(userDTO.getEmail());
                    if (list != null) {
                        //hash map để chứa foodID và currentQuantity
                        HashMap<TblCartDetailDTO, Integer> foodMap = new HashMap<>();
                        for (TblCartDetailDTO dto : list) {
                            //chỉnh sửa foodID lại thành foodName
                            String foodID = dto.getFoodID();
                            String foodName = foodDAO.getFoodNameByFoodID(foodID);
                            dto.setFoodID(foodName);
                            //đưa foodID(foodName) và currentQuantity vào HashMap
                            TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(foodID);
                            if (foodDTO != null) {
                                int currenQuantity = foodDTO.getCurrentQuantity();
                                foodMap.put(dto, currenQuantity);
                            }
                        }
                        request.setAttribute("FOOD_MAP", foodMap);
                        request.setAttribute("FOOD_IN_CART", list);
                    }

                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
            url = "SumOfCartServlet";
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

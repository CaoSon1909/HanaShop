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
import sonpc.daos.tblOrderDetailDAO;
import sonpc.dtos.TblCartDetailDTO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblOrderDTO;
import sonpc.dtos.TblUserDTO;
import sonpc.dtos.tblOrderDetailDTO;

/**
 *
 * @author ACER
 */
public class FillOrderServlet extends HttpServlet {

    private final String THANK_YOU_PAGE = "thankyou.jsp";
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
        String url = "";
        try {
            String error = (String) request.getAttribute("ERROR_MSG");
            if (error != null) {
                request.setAttribute("ERROR_MSG", error);
                url = CONFIRM_PAGE;
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                //1: lấy order ra
                TblOrderDTO order = (TblOrderDTO) request.getAttribute("ORDER");
                if (order != null) {
                    //2: tạo order detail DTO (ID, orderID(lấy từ order), (foodID, quantity, price): lấy từ showCustomerCart)
                    String orderID = order.getID();
                    //3: lấy (foodID, quantity, price) từ showCustomerCart (tblCartDetailDAO)
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        TblUserDTO user = (TblUserDTO) session.getAttribute("USER_DTO");
                        TblCartDetailDAO cartDetailDAO = new TblCartDetailDAO();
                        //do showCustomerCart cần userEmail => lấy userEmail từ session
                        List<TblCartDetailDTO> customerCart = cartDetailDAO.showCustomerCart(user.getEmail());
                        if (customerCart != null) {
                            for (TblCartDetailDTO dto : customerCart) {
                                //lấy đc foodID, quantity và price
                                String foodID = dto.getFoodID();
                                int quantity = dto.getQuantity();
                                float price = dto.getPrice();

                                // tạo order detail đc rồi
                                tblOrderDetailDAO dao = new tblOrderDetailDAO();
                                boolean result = dao.createOrderDetail(orderID, foodID, quantity, price);
                                if (result) {
                                    //tạo order detail thành công, xóa cart detail đi
                                    boolean removeResult = cartDetailDAO.deleteCart(foodID);
                                    if (removeResult) {
                                        TblFoodDAO foodDAO = new TblFoodDAO();
                                        //đồng thời cập nhật currentQuantity trong tbl_Food
                                        boolean updateResult = foodDAO.updateCurrentQuantityAfterCheckout(orderID, foodID);
                                        if (updateResult) {
                                            //set foodID thành foodName
                                            TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(foodID);
                                            if (foodDTO != null) {
                                                String foodName = foodDTO.getName();
                                                //Lấy order detail ở DB ra để hiển thị bên trang thank you
                                                tblOrderDetailDAO detailDAO = new tblOrderDetailDAO();
                                                List<tblOrderDetailDTO> detailList = detailDAO.getOrderDetailByOrderID(orderID);
                                                if (detailList != null) {
                                                    //chỉnh sửa foodID thành foodName của order detail, setattr vào req scope liền
                                                    for (tblOrderDetailDTO detailDTO : detailList) {
                                                        detailDTO.setFoodID(foodName);
                                                    }
                                                    request.setAttribute("ORDER_DETAIL_LIST", detailList);
                                                }//end if detailList != null
                                            }//end if dto != null
                                        }//end if update currentQuantity success
                                    }//end if remove cart success
                                }//end if create order detail success
                            }//end for cart dto : cart list
                            
                            request.setAttribute("THANK_YOU_MESSAGE", "Check out success! Thank you for buying our product");
                            request.setAttribute("ORDER", order);
                        }//end if customer cart not null
                    }//end if session not null
                }
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
            url = THANK_YOU_PAGE;
            //forward sang trang cảm ơn
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

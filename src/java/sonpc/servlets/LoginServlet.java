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
import sonpc.daos.TblUserDAO;
import sonpc.dtos.TblFoodCategoryDTO;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.TblUserDTO;
import sonpc.errors.LoginErr;

/**
 *
 * @author ACER
 */
public class LoginServlet extends HttpServlet {

    private final String LOGIN_DYNAMIC_PAGE = "login.jsp";
    private final String SEARCH_DYNAMIC_PAGE_FOR_ADMIN = "adminSearch.jsp";
    private final String SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER = "customerSearch.jsp";

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
        String url = LOGIN_DYNAMIC_PAGE; //mặc định là trang login.html
        try {
            String userEmail = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");

            TblUserDAO userDAO = new TblUserDAO();
            TblUserDTO dto = userDAO.checkLogin(userEmail, password);
            if (dto != null) {
                //lưu dto vào session luôn
                HttpSession session = request.getSession();
                session.setAttribute("USER_DTO", dto);
                int roleID = ((TblUserDTO) session.getAttribute("USER_DTO")).getRoleID();//có thể getRoleID thông qua dto cũng đc
                if (roleID == 1) {
                    //user is admin
                    //get food list from DB
                    TblFoodDAO foodDAO = new TblFoodDAO();
                    List<TblFoodDTO> foodList = foodDAO.getAllActivatedFood();
                    //get category list from DB
                    TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                    List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                    if (foodList != null) {
                        //1: food list
                        request.setAttribute("FOOD_LIST", foodList);
                        //2: category list
                        if (categoryList != null) {
                            request.setAttribute("CATEGORY_LIST", categoryList);
                        }
                    }
                    request.getRequestDispatcher(SEARCH_DYNAMIC_PAGE_FOR_ADMIN).forward(request, response);
                }
                if (roleID == 2) {
                    //user is customer
                    //get food list from DB
                    TblFoodDAO foodDAO = new TblFoodDAO();
                    List<TblFoodDTO> foodList = foodDAO.getAllActivatedFood();
                    //get category list
                    TblFoodCategoryDAO categoryDAO = new TblFoodCategoryDAO();
                    List<TblFoodCategoryDTO> categoryList = categoryDAO.loadFoodCategory();
                    if (foodList != null) {
                        //1: food list
                        request.setAttribute("FOOD_LIST", foodList);
                        //2: category list
                        if (categoryList != null) {
                            request.setAttribute("CATEGORY_LIST", categoryList);
                        }
                    }
                    request.getRequestDispatcher(SEARCH_DYNAMIC_PAGE_FOR_CUSTOMER).forward(request, response);
                }
            } else {
                //login failed
                LoginErr errMsg = new LoginErr();
                errMsg.setErrorMessage("Invalid email or password. Please double-check both again!!");
                request.setAttribute("LOGIN_ERR", errMsg);
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (SQLException | NamingException ex) {
            log(ex.getMessage());
        } finally {
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

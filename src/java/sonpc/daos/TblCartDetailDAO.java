/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonpc.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.dtos.TblCartDetailDTO;
import sonpc.dtos.TblFoodDTO;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblCartDetailDAO implements Serializable {

    //check trong giỏ hàng xem có tồn tại foodID chưa, nếu đã tồn tại thì 
    //UPDATE tbl_CartDetail SET quantity = ? WHERE userEmail = ? AND foodID = ?
    public List<TblCartDetailDTO> getAllFoodFromCart() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblCartDetailDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, userEmail, foodID, quantity, price FROM tbl_CartDetail";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String userEmail = rs.getString("userEmail");
                    String foodID = rs.getString("foodID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    TblCartDetailDTO dto = new TblCartDetailDTO(ID, userEmail, foodID, quantity, price);
                    list.add(dto);
                }
                return list;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean checkExistedFoodInCart(String userEmail, String foodID) throws NamingException, SQLException {
        List<TblCartDetailDTO> list = getAllFoodFromCart();
        if (list != null) {
            for (TblCartDetailDTO dto : list) {
                if (dto.getUserEmail().equals(userEmail) && dto.getFoodID().equals(foodID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addToCartLater(String userEmail, String foodID, int quantity) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {

                String sql = "UPDATE tbl_CartDetail SET quantity = ? WHERE userEmail = ? AND foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, userEmail);
                ps.setString(3, foodID);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean addToCartFirstTime(String userEmail, String foodID, int quantity, float price) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {

                String sql = "INSERT INTO tbl_CartDetail (userEmail, foodID, quantity, price) VALUES (?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setString(2, foodID);
                ps.setInt(3, quantity);
                ps.setFloat(4, price);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getCurrentQuantity(String userEmail, String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT quantity FROM tbl_CartDetail WHERE userEmail = ? AND foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setString(2, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    return quantity;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<TblCartDetailDTO> showCustomerCart(String userEmail) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblCartDetailDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, userEmail, foodID, quantity, price FROM tbl_CartDetail WHERE userEmail = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String foodID = rs.getString("foodID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    TblCartDetailDTO dto = new TblCartDetailDTO(ID, userEmail, foodID, quantity, price);

                    list.add(dto);
                }
                return list;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean deleteCart(String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE tbl_CartDetail WHERE foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateQuantity(String cartID, int quantity, String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int currentQuantity = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                TblFoodDAO foodDAO = new TblFoodDAO();
                TblFoodDTO food = foodDAO.getFoodByFoodID(foodID);
                if (food != null) {
                    currentQuantity = food.getCurrentQuantity();
                    if (quantity < currentQuantity) {
                        String sql = "UPDATE tbl_CartDetail SET quantity = ? WHERE ID = ?";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, quantity);
                        ps.setString(2, cartID);
                        int row = ps.executeUpdate();
                        if (row > 0){
                            return true;
                        }
                    }
                }
            }
        } finally {
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }

    public TblCartDetailDTO getCartByEmailAndFoodID(String userEmail, String foodID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "SELECT ID, userEmail, foodID, quantity, price FROM tbl_CartDetail WHERE userEmail = ? AND foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setString(2, foodID);
                rs = ps.executeQuery();
                if (rs.next()){
                    String id = rs.getString("ID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    
                    TblCartDetailDTO dto = new TblCartDetailDTO(id, userEmail, foodID, quantity, price);
                    
                    return dto;
                }
            }
        }
        finally{
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (con != null){
                con.close();
            }
        }
        return null;
    }
    
}

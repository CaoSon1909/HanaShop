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
import sonpc.dtos.tblOrderDetailDTO;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class tblOrderDetailDAO implements Serializable {

    public boolean createOrderDetail(String orderID, String foodID, int quantity, float price) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_OrderDetail (orderID, foodID, quantity, price) VALUES (?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
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

    public List<tblOrderDetailDTO> getOrderDetailByOrderID(String orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<tblOrderDetailDTO> list = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, orderID, foodID, quantity, price FROM tbl_OrderDetail WHERE orderID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String foodID = rs.getString("foodID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    tblOrderDetailDTO dto = new tblOrderDetailDTO(ID, orderID, foodID, quantity, price);

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

    public tblOrderDetailDTO getOrderDetailByOrderIDAndFoodID(String orderID, String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, orderID, foodID, quantity, price FROM tbl_OrderDetail WHERE orderID = ? AND foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                ps.setString(2, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String ID = rs.getString("ID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    tblOrderDetailDTO dto = new tblOrderDetailDTO(ID, orderID, foodID, quantity, price);

                    return dto;
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
        return null;
    }

    public List<tblOrderDetailDTO> getOrderDetailByFoodID(String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<tblOrderDetailDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, orderID, foodID, quantity, price FROM tbl_OrderDetail WHERE foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String orderID = rs.getString("orderID");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");

                    tblOrderDetailDTO dto = new tblOrderDetailDTO(ID, orderID, foodID, quantity, price);

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

    //lấy những order detail nằm riêng ở những order khác nhau, ko lấy những order detail nằm cùng 1 order
    public List<String> getDistinctOrderIDList(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT DISTINCT(orderID) FROM tbl_OrderDetail WHERE foodID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String orderID = rs.getString("orderID");
                    list.add(orderID);
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
        return list;
    }
}

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
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.dtos.TblOrderDTO;
import sonpc.formatters.DateFormatter;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblOrderDAO implements Serializable {

    public boolean createOrder(String deliveryAddress, float total, long orderDate, String userEmail, int paymentType) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {

                String sql = "INSERT INTO tbl_Order (deliveryAddress, total, orderDate, status, userEmail, paymentTypeID) VALUES "
                        + "(?,?,?,1,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, deliveryAddress);
                ps.setFloat(2, total);
                ps.setLong(3, orderDate);
                ps.setString(4, userEmail);
                ps.setInt(5, paymentType);
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

    public TblOrderDTO getOrderByEmailAndOrderDate(String userEmail, long orderDate) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, deliveryAddress, total, orderDate, status, userEmail, paymentTypeID FROM "
                        + "tbl_Order WHERE userEmail = ? AND orderDate = ? "
                        + "ORDER BY orderDate ASC";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                ps.setLong(2, orderDate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String ID = rs.getString("ID");
                    String address = rs.getString("deliveryAddress");
                    float total = rs.getFloat("total");
                    int status = rs.getInt("status");
                    int paymentTypeID = rs.getInt("paymentTypeID");

                    TblOrderDTO dto = new TblOrderDTO(ID, address, total, orderDate + "", status, userEmail, paymentTypeID);

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

    public List<TblOrderDTO> getOrderListByEmail(String userEmail) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblOrderDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, deliveryAddress, total, orderDate, status, userEmail, paymentTypeID "
                        + "FROM tbl_Order WHERE userEmail = ? "
                        + "ORDER BY orderDate ASC";
                ps = con.prepareStatement(sql);
                ps.setString(1, userEmail);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String deliveryAddress = rs.getString("deliveryAddress");
                    float total = rs.getFloat("total");
                    long orderDateLong = rs.getLong("orderDate");
                    Date orderDate = new Date(orderDateLong);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(orderDate);
                    int status = rs.getInt("status");
                    int paymentTypeID = rs.getInt("paymentTypeID");

                    TblOrderDTO dto = new TblOrderDTO(ID, deliveryAddress, total, dateString, status, userEmail, paymentTypeID);

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

    public TblOrderDTO getOrderByOrderID(String orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, deliveryAddress, total, orderDate, status, userEmail, paymentTypeID FROM "
                        + "tbl_Order WHERE ID = ? "
                        + "ORDER BY orderDate ASC";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String ID = rs.getString("ID");
                    String address = rs.getString("deliveryAddress");
                    float total = rs.getFloat("total");
                    long dateLong = rs.getLong("orderDate");
                    Date date = new Date(dateLong);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(date);
                    int status = rs.getInt("status");
                    String userEmail = rs.getString("userEmail");
                    int paymentTypeID = rs.getInt("paymentTypeID");

                    TblOrderDTO dto = new TblOrderDTO(ID, address, total, dateString, status, userEmail, paymentTypeID);

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
    
    
    public List<TblOrderDTO> getOrderListCustomerBuy(String orderID) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblOrderDTO> list = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "SELECT ID, deliveryAddress, total, orderDate, status, userEmail, paymentTypeID "
                        + "FROM tbl_Order WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, orderID);
                rs = ps.executeQuery();
                while (rs.next()){
                    String id = rs.getString("ID");
                    String deliveryAddress = rs.getString("deliveryAddress");
                    float total = rs.getFloat("total");
                    long dateLong = rs.getLong("orderDate");
                    Date date = new Date(dateLong);
                    //
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(date);
                    //
                    int status = rs.getInt("status");
                    String userEmail = rs.getString("userEmail");
                    int paymentTypeID = rs.getInt("paymentTypeID");
                    
                    if (list == null){
                        list = new Vector<>();
                    }
                    
                    TblOrderDTO dto = new TblOrderDTO(id, deliveryAddress, total, dateString, status, userEmail, paymentTypeID);
                    list.add(dto);
                }
                return list;
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

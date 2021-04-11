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
import javax.naming.NamingException;
import sonpc.dtos.TblUserDTO;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if (con != null){
                String sql = "Select fullname, address, phoneNum, dateOfBirth, gender, status, roleID "
                        + "From tbl_User Where email = ? And password = ? "
                        + "AND status = 1";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()){
                    String fullName = rs.getString("fullname");
                    String address = rs.getString("address");
                    String phoneNum = rs.getString("phoneNum");
                    long dateLong = rs.getLong("dateOfBirth");
                    java.util.Date dateOfBirth = new Date(dateLong);
                    int gender = rs.getInt("gender");
                    int status = rs.getInt("status");
                    int roleID = rs.getInt("roleID");
                    TblUserDTO dto = new TblUserDTO(email, password, fullName, address, phoneNum, dateOfBirth, gender, status, roleID);
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

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
import sonpc.dtos.TblFoodCategoryDTO;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblFoodCategoryDAO implements Serializable {

    public List<TblFoodCategoryDTO> loadFoodCategory() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblFoodCategoryDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description FROM tbl_FoodCategory";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null){
                        list = new Vector<>();
                    }
                    String id = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    
                    TblFoodCategoryDTO dto = new TblFoodCategoryDTO(id, name, description);
                    
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

}

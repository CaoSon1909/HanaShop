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
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import sonpc.dtos.TblFoodDTO;
import sonpc.dtos.tblOrderDetailDTO;
import sonpc.formatters.DateFormatter;
import sonpc.utils.DBHelpers;

/**
 *
 * @author ACER
 */
public class TblFoodDAO implements Serializable {

    public List<TblFoodDTO> getAllActivatedFood() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblFoodDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food "
                        + "WHERE status = 1 AND currentQuantity > 0 ORDER BY createDate ASC";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    long date = rs.getLong("createDate");
                    java.util.Date createDate = new Date(date);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(createDate);
                    int currentQuantity = rs.getInt("currentQuantity");
                    int categoryID = rs.getInt("categoryID");

                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, dateString, currentQuantity, categoryID, 1);

                    if (list == null) {
                        list = new Vector<>();
                    }
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

    public List<TblFoodDTO> searchFoodByName(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblFoodDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food "
                        + "WHERE status = 1 AND currentQuantity > 0 AND name LIKE ? "
                        + "ORDER BY createDate ASC";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    long date = rs.getLong("createDate");
                    java.util.Date createDate = new Date(date);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(createDate);
                    int currentQuantity = rs.getInt("currentQuantity");
                    int categoryID = rs.getInt("categoryID");

                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, dateString, currentQuantity, categoryID, 1);

                    if (list == null) {
                        list = new Vector<>();
                    }
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

    public List<TblFoodDTO> searchFoodByPriceRange(float minPrice, float maxPrice) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblFoodDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food "
                        + "WHERE status = 1 AND currentQuantity > 0 AND price BETWEEN ? AND ? "
                        + "ORDER BY createDate ASC";
                ps = con.prepareStatement(sql);
                ps.setFloat(1, minPrice);
                ps.setFloat(2, maxPrice);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    long date = rs.getLong("createDate");
                    java.util.Date createDate = new Date(date);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(createDate);
                    int currentQuantity = rs.getInt("currentQuantity");
                    int categoryID = rs.getInt("categoryID");
                    int status = rs.getInt("status");

                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, dateString, currentQuantity, categoryID, status);

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

    public List<TblFoodDTO> searchFoodByCategoryID(int searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TblFoodDTO> list = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food "
                        + "WHERE status = 1 AND currentQuantity > 0 "
                        + "AND categoryID = ? ORDER BY createDate ASC";
                ps = con.prepareStatement(sql);
                ps.setInt(1, searchValue);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (list == null) {
                        list = new Vector<>();
                    }
                    String ID = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    long date = rs.getLong("createDate");
                    java.util.Date createDate = new Date(date);
                    DateFormatter formatter = new DateFormatter();
                    String dateString = formatter.formatDateToString(createDate);
                    int currentQuantity = rs.getInt("currentQuantity");
                    int categoryID = rs.getInt("categoryID");
                    int status = rs.getInt("status");

                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, dateString, currentQuantity, categoryID, status);

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

    public boolean updateFood(String ID, String name, String description, float price, int currentQuantity, int categoryID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Food SET name = ?, description = ?, price = ?, currentQuantity = ?, categoryID = ? "
                        + "WHERE ID = ? AND status = 1";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setFloat(3, price);
                ps.setInt(4, currentQuantity);
                ps.setInt(5, categoryID);
                ps.setString(6, ID);

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

    public boolean deleteFood(String ID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE tbl_Food SET status = 0 WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, ID);
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
                ps.close();
            }
        }
        return false;
    }

    public boolean createFood(String foodName, String foodDescription, float foodPrice, int foodQuantity, int foodCategory) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_Food (name, description, price, createDate, currentQuantity, categoryID, status) VALUES "
                        + "(?,?,?,?,?,?,1)";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodName);
                ps.setString(2, foodDescription);
                ps.setFloat(3, foodPrice);
                ps.setLong(4, new Date().getTime());
                ps.setInt(5, foodQuantity);
                ps.setInt(6, foodCategory);

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

    public String getFoodNameByFoodID(String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT name FROM tbl_Food WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String foodName = rs.getString("name");
                    return foodName;
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

    public TblFoodDTO getFoodByFoodID(String foodID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, foodID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("ID");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    long date = rs.getLong("createDate");
                    String dateString = date + "";
                    int currentQuantity = rs.getInt("currentQuantity");
                    int categoryID = rs.getInt("categoryID");
                    int status = rs.getInt("status");

                    TblFoodDTO dto = new TblFoodDTO(id, name, description, price, dateString, currentQuantity, categoryID, status);

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

    public boolean updateCurrentQuantityAfterCheckout(String orderID, String foodID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int quantity = 0;
        int currentQuantity = 0;
        int newQuantity = 0;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {

                //get Order detail dto => get quantity
                tblOrderDetailDAO dao = new tblOrderDetailDAO();
                tblOrderDetailDTO dto = dao.getOrderDetailByOrderIDAndFoodID(orderID, foodID);
                if (dto != null) {
                    quantity = dto.getQuantity();
                }
                //get food dto => get currentQuantity
                TblFoodDAO foodDAO = new TblFoodDAO();
                TblFoodDTO foodDTO = foodDAO.getFoodByFoodID(foodID);
                if (foodDTO != null) {
                    currentQuantity = foodDTO.getCurrentQuantity();
                }
                //quantity mới = currentQuantity - quantity
                newQuantity = currentQuantity - quantity;
                if (newQuantity > 0) {
                    //new Quantity ko đc <= 0
                    String sql = "UPDATE tbl_Food SET currentQuantity = ? WHERE ID = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, newQuantity);
                    ps.setString(2, foodID);

                    int row = ps.executeUpdate();
                    if (row > 0) {
                        return true;
                    }
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

    /*===========================================================*/
//    private final int ROWS_IN_PAGE = 3;
//
//    public List<TblFoodDTO> getActivatedFood(int offset) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID, status FROM tbl_Food "
//                        + "WHERE status = 1 AND currentQuantity > 0 "
//                        + "ORDER BY createDate ASC "
//                        + "OFFSET ? ROWS "
//                        + "FETCH NEXT ? ROWS ONLY";
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, offset);
//                ps.setInt(2, ROWS_IN_PAGE);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    if (list == null) {
//                        list = new Vector<>();
//                    }
//                    String ID = rs.getString("ID");
//                    String name = rs.getString("name");
//                    String description = rs.getString("description");
//                    float price = rs.getFloat("price");
//                    long date = rs.getLong("createDate");
//                    java.util.Date createDate = new Date(date);
//                    DateFormatter formatDateToString = new DateFormatter();
//                    String dateString = formatDateToString.formatDateToString(createDate);
//                    int currentQuantity = rs.getInt("currentQuantity");
//                    int categoryID = rs.getInt("categoryID");
//                    int status = rs.getInt("status");
//
//                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, dateString, currentQuantity, categoryID, status);
//
//                    list.add(dto);
//                }
//                return list;
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
//
//    public int countActivatedFood() throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "SELECT COUNT(ID) AS MAX_PAGE FROM tbl_Food WHERE status = 1 AND currentQuantity > 0";
//                ps = con.prepareStatement(sql);
//                rs = ps.executeQuery();
//                if (rs.next()) {
//                    int maxPage = rs.getInt("MAX_PAGE");
//                    if (maxPage > 0) {
//                        return maxPage;
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return 0;
//    }
//
//    //search rồi paging cần 3 function
//    //1: get all food from DB then paging
//    public int getSizeAllFood() throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int size = 0;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//
//                String sql = "SELECT COUNT(ID) AS SUM FROM tbl_Food WHERE status = 1 AND currentQuantity > 0";
//                ps = con.prepareStatement(sql);
//                rs = ps.executeQuery();
//                if (rs.next()) {
//                    size = rs.getInt("SUM");
//                    if (size > 0) {
//                        return size;
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return 0;
//    }
//
//    public int getNumberOfPagesDefault(int sumOfRecords) throws NamingException, SQLException {
//        int numberOfPages = 0;
//        sumOfRecords = getSizeAllFood();
//        if (sumOfRecords > 0) {
//            if ((sumOfRecords % ROWS_IN_PAGE) > 0) {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE) + 1;
//            } else {
//                numberOfPages = sumOfRecords / ROWS_IN_PAGE;
//            }
//
//        }
//        return numberOfPages;
//    }
//
//    public List<TblFoodDTO> pagingFood(int pageIndex) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        //tổng số dòng trong 1 page mà mình muốn => ROWS_IN_PAGE
//
//        //tổng số dòng của record lấy từ DB
//        int sumOfRecords = 0;
//        //tổng số page cần để display những record
//        int numberOfPages = 0;
//
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select ID, name, description, price, createDate, currentQuantity, categoryID From tbl_Food "
//                        + "Where status = 1 AND currentQuantity > 0 "
//                        + "Order By createDate ASC "
//                        + "OFFSET ? rows "
//                        + "Fetch First ? rows only;";
//                ps = con.prepareStatement(sql);
//                // offset: bước nhảy của con trỏ, VD: nhảy 3 dòng thì sẽ lấy dòng thứ 4
//                // Fetch First (..) rows : lấy bao nhiêu dòng = số dòng của 1 trang
//
//                // tính offset
//                sumOfRecords = getSizeAllFood(); //12
//                numberOfPages = getNumberOfPagesDefault(sumOfRecords);
//                int offset = (pageIndex - 1) * numberOfPages;
//                ps.setInt(1, offset);
//                ps.setInt(2, ROWS_IN_PAGE);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    String ID = rs.getString("ID");
//                    String name = rs.getString("name");
//                    String description = rs.getString("description");
//                    float price = rs.getFloat("price");
//                    long date = rs.getLong("createDate");
//                    java.util.Date createDate = new Date(date);
//                    int currentQuantity = rs.getInt("currentQuantity");
//                    int categoryID = rs.getInt("categoryID");
//
//                    TblFoodDTO dto = new TblFoodDTO(ID, name, description, price, createDate, currentQuantity, categoryID, 1);
//                    if (list == null) {
//                        list = new Vector<>();
//                    }
//                    list.add(dto);
//                }
//                return list;
//            }//end if con
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
//
//    //2: search food by name then paging
//    public int getSizeSearchByName(String foodName) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select COUNT(ID) AS SUM From tbl_Food Where status = 1 AND name Like ?";
//
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + foodName + "%");
//
//                rs = ps.executeQuery();
//                if (rs.next()) {
//                    int size = rs.getInt("SUM");
//                    if (size > 0) {
//                        return size;
//                    }
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                ps.close();
//            }
//        }
//        return 0;
//    }
//
//    public int getNumberOfPagesSearchByName(String foodName, int sumOfRecords) throws NamingException, SQLException {
//        int numberOfPages = 0;
//        sumOfRecords = getSizeSearchByName(foodName);
//        if (sumOfRecords > 0) {
//            if ((sumOfRecords % ROWS_IN_PAGE) > 0) {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE) + 1;
//            } else {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE);
//            }
//            return numberOfPages;
//        }
//        return 0;
//    }
//
//    public List<TblFoodDTO> pagingSearchByName(int pageIndex, String foodName) throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        //tổng record trong DB
//        int sumOfRecords = 0;
//        //tổng số dòng trong 1 trang mà mình muốn => ROWS_IN_PAGE
//
//        //tổng số trang cần để display những record
//        int numberOfPages = 0;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "SELECT ID, name, description, price, createDate, currentQuantity, categoryID FROM "
//                        + "tbl_Food WHERE status = 1 AND name LIKE ? "
//                        + "ORDER BY createDate "
//                        + "OFFSET ? ROWS "
//                        + "FETCH NEXT ? ROWS ONLY";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, "%" + foodName + "%");
//                // tính offset
//                sumOfRecords = getSizeSearchByName(foodName);
//                if (sumOfRecords > 0) {
//                    numberOfPages = getNumberOfPagesSearchByName(foodName, sumOfRecords);
//                    if (numberOfPages > 0) {
//                        int offset = (pageIndex - 1) * numberOfPages;
//                        if (offset >= 0) {
//
//                            ps.setInt(2, offset);
//                            ps.setInt(3, ROWS_IN_PAGE);
//
//                            rs = ps.executeQuery();
//                            while (rs.next()) {
//                                String id = rs.getString("ID");
//                                String name = rs.getString("name");
//                                String description = rs.getString("description");
//                                float price = rs.getFloat("price");
//                                long date = rs.getLong("createDate");
//                                java.util.Date createDate = new Date(date);
//                                int currentQuantity = rs.getInt("currentQuantity");
//                                int categoryID = rs.getInt("categoryID");
//
//                                TblFoodDTO dto = new TblFoodDTO(id, name, description, price, createDate, currentQuantity, categoryID, 1);
//
//                                if (list == null) {
//                                    list = new Vector<>();
//                                }
//                                list.add(dto);
//
//                            }
//                            return list;
//                        }//end if offset
//                    }//end if numberOfPages
//                }//end if sumOfRecords
//            }//end if con
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
//
//    //3: search food in range then paging
//    public int getSizeSearchByPriceRange(float minPrice, float maxPrice) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select COUNT(ID) From tbl_Food\n"
//                        + "Where status =1 AND price Between ? And ?";
//                ps.setFloat(1, minPrice);
//                ps.setFloat(2, maxPrice);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    if (list == null) {
//                        list = new Vector<>();
//                    }
//                    String id = rs.getString("ID");
//                    String name = rs.getString("name");
//                    String description = rs.getString("description");
//                    float price = rs.getFloat("price");
//                    long date = rs.getLong("createDate");
//                    java.util.Date creataDate = new Date(date);
//                    int currentQuantity = rs.getInt("currentQuantity");
//                    int categoryID = rs.getInt("categoryID");
//
//                    TblFoodDTO dto = new TblFoodDTO(id, name, description, price, creataDate, currentQuantity, categoryID, 1);
//
//                    list.add(dto);
//                }
//                return list.size();
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return 0;
//    }
//
//    public int getNumberOfPagesSearchByRangeMoney(float minPrice, float maxPrice, int sumOfRecords) throws NamingException, SQLException {
//        int numberOfPages = 0;
//        sumOfRecords = getSizeSearchByPriceRange(minPrice, maxPrice);
//        if (sumOfRecords > 0) {
//            if ((sumOfRecords % ROWS_IN_PAGE) > 0) {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE) + 1;
//            } else {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE);
//            }
//            return numberOfPages;
//        }
//        return 0;
//    }
//
//    public List<TblFoodDTO> pagingFoodByRangeOfMoney(int pageIndex, float minPrice, float maxPrice) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        //để phân trang,cần offset. Offset = (pageIndex - 1)* numberOfPages(số trang cần để display toàn bộ record lấy từ DB)
//        //numberOfPages = (tổng số record lấy từ DB / số dòng trong 1 trang mà mình muốn) (+) 1;
//        int numberOfPages = 0;
//        int sumOfRecords = 0;
//        //
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select ID, name, description, price, createDate, currentQuantity, categoryID From tbl_Food\n"
//                        + "Where status =1 AND price Between ? And ? Order By price ASC\n"
//                        + "OFFSET ? ROWS\n"
//                        + "FETCH NEXT ? ROWS ONLY";
//                ps.setFloat(1, minPrice);
//                ps.setFloat(2, maxPrice);
//                //offset
//                sumOfRecords = getSizeSearchByPriceRange(minPrice, maxPrice);
//                if (sumOfRecords > 0) {
//                    numberOfPages = getNumberOfPagesSearchByRangeMoney(minPrice, maxPrice, sumOfRecords);
//
//                    if (numberOfPages > 0) {
//                        int offset = (pageIndex - 1) * numberOfPages;
//
//                        if (offset >= 0) {
//                            ps.setInt(3, offset);
//                            ps.setInt(4, ROWS_IN_PAGE);
//
//                            rs = ps.executeQuery();
//                            while (rs.next()) {
//                                String id = rs.getString("ID");
//                                String name = rs.getString("name");
//                                String description = rs.getString("description");
//                                float price = rs.getFloat("price");
//                                long date = rs.getLong("createDate");
//                                java.util.Date createDate = new Date(date);
//                                int currentQuantity = rs.getInt("currentQuantity");
//                                int categoryID = rs.getInt("categoryID");
//
//                                TblFoodDTO dto = new TblFoodDTO(id, name, description, price, createDate, currentQuantity, categoryID, 1);
//
//                                if (list == null) {
//                                    list = new Vector<>();
//                                }
//                                list.add(dto);
//                            }
//                            return list;
//                        }//end if offset
//                    }//end if numberOfPages
//                }//end if sumOfRecords
//            }//end if con
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
//
//    //4: search food by category then paging
//    public int getSizeSearchByCategoryResult(int categoryID) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select COUNT(ID) From tbl_Food "
//                        + "Where status = 1 AND categoryID = ?";
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, categoryID);
//                rs = ps.executeQuery();
//                while (rs.next()) {
//                    if (list == null) {
//                        list = new Vector<>();
//                    }
//                    String id = rs.getString("ID");
//                    String name = rs.getString("name");
//                    String description = rs.getString("description");
//                    float price = rs.getFloat("price");
//                    long date = rs.getLong("createDate");
//                    java.util.Date createDate = new Date(date);
//                    int currentQuantity = rs.getInt("currentQuantity");
//
//                    TblFoodDTO dto = new TblFoodDTO(id, name, description, price, createDate, currentQuantity, categoryID, 1);
//
//                    list.add(dto);
//                }
//                return list.size();
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return 0;
//    }
//
//    public int getNumberOfPagesSearchByCategory(int categoryID, int sumOfRecords) throws NamingException, SQLException {
//        int numberOfPages = 0;
//        sumOfRecords = getSizeSearchByCategoryResult(categoryID);
//        if (sumOfRecords > 0) {
//            if ((sumOfRecords % ROWS_IN_PAGE) > 0) {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE) + 1;
//            } else {
//                numberOfPages = (sumOfRecords / ROWS_IN_PAGE);
//            }
//            return numberOfPages;
//        }
//        return 0;
//    }
//
//    public List<TblFoodDTO> pagingFoodByCategory(int categoryID, int pageIndex) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<TblFoodDTO> list = null;
//        //paging
//        int sumOfRecords = 0;
//        // ROWS_IN_PAGE
//        //
//        int numberOfPages = 0;
//        //
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Select ID, name, description, price, createDate, currentQuantity From tbl_Food "
//                        + "Where status = 1 AND categoryID = ? Order By createDate ASC"
//                        + "OFFSET ? ROWS\n"
//                        + "FETCH NEXT ? ROWS ONLY";
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, categoryID);
//                //tính offset
//                sumOfRecords = getSizeSearchByCategoryResult(categoryID);
//                if (sumOfRecords > 0) {
//                    //numberOfPages
//                    numberOfPages = getNumberOfPagesSearchByCategory(categoryID, sumOfRecords);
//                    if (numberOfPages > 0) {
//                        //offset
//                        int offset = (pageIndex - 1) * numberOfPages;
//                        if (offset >= 0) {
//
//                            ps.setInt(2, offset);
//                            ps.setInt(3, ROWS_IN_PAGE);
//                            rs = ps.executeQuery();
//                            while (rs.next()) {
//                                if (list == null) {
//                                    list = new Vector<>();
//                                }
//                                String id = rs.getString("ID");
//                                String name = rs.getString("name");
//                                String description = rs.getString("description");
//                                float price = rs.getFloat("price");
//                                long date = rs.getLong("createDate");
//                                java.util.Date createDate = new Date(date);
//                                int currentQuantity = rs.getInt("currentQuantity");
//                                TblFoodDTO dto = new TblFoodDTO(id, name, description, price, createDate, currentQuantity, categoryID, 1);
//                                list.add(dto);
//                            }
//                            return list;
//                        }//end if offset
//                    }//end if numberOfPages
//                }//end if sumOfRecords
//            }//end if con
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
}

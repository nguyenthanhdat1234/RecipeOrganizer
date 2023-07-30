/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.recipe;

import java.io.Serializable;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javax.naming.NamingException;
import team3.DTO.FavoriteDTO;
import team3.DTO.RatingDTO;
import team3.DTO.RecipeDTO;
import team3.DTO.ShowRatingDTO;
import team3.DTO.StepsDTO;
import team3.util.DBUtils;
import team3.recipe.RecipeOrganizeDTO;

/**
 *
 * @author THIS PC
 */
public class RecipeOrganizeDAO implements Serializable {

    public RecipeOrganizeDTO checkLogin(String userName, String password)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From account "
                        + "Where userName = ? "
                        + "AND password = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setString(2, password);

                rs = stm.executeQuery();

                if (rs.next()) {

                    int userID = rs.getInt("userID");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    boolean role = rs.getBoolean("role");
                    int status = rs.getInt("status");
                    String token = rs.getString("token");
                    String email = rs.getString("email");
                    String image_path = rs.getString("image_path");
                    result = new RecipeOrganizeDTO(userID, userName, password, fullName, phone, status, role, token, email, image_path);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public List<RecipeOrganizeDTO> searchRecipe(String txtSearch) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RecipeOrganizeDTO> list = new ArrayList<>();
        try {
            String query = "SELECT *\n"
                    + "FROM recipe\n"
                    + "WHERE  recipeName like ?  ";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + txtSearch + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                RecipeOrganizeDTO o = new RecipeOrganizeDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7)
                );
                list.add(o);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<RecipeOrganizeDTO> getAllRecipe() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RecipeOrganizeDTO> list = new ArrayList<>();
        String query = "SELECT * from recipe";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareCall(query);;
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RecipeOrganizeDTO(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
        } catch (Exception e) {
        }

        return list;
    }

    public boolean SignUp(String username, String password, String fullname, String phone, int status, boolean role, String token, String email)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        String query = "INSERT into account(userName, password, fullName, phone, status, role, token, email)"
                + " Values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);// phần userID là tăng dần ko cần phải tự tay chèn
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, fullname);
            stm.setString(4, phone);
            stm.setInt(5, status);
            stm.setBoolean(6, role);
            stm.setString(7, token);
            stm.setString(8, email);
            int row = stm.executeUpdate();

            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    public List<String> getEmailToCheck(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        String query = "Select email "
                + "From account "
                + "Where email = ? ";

        try {
            con = new DBUtils().getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();

            while (rs.next()) {

                list.add(rs.getString("email"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public RecipeOrganizeDTO getTokenToResetPassword(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;
        String query = "Select userID, token, email "
                + "From account "
                + "Where email = ? ";

        try {
            con = new DBUtils().getConnection();
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("userID");
                String token = rs.getString("token");
                result = new RecipeOrganizeDTO(userID, token, email);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean resetPassword(String password, String email, String token) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = new DBUtils().getConnection();
            if (con != null) {
                String query = "UPDATE account "
                        + "SET password = ? "
                        + "WHERE email = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, password);
                stm.setString(2, email);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public RecipeOrganizeDTO loginByGmail(String email)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From account "
                        + "Where email = ? ";

                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();

                if (rs.next()) {

                    int userID = rs.getInt("userID");
                    String userName = rs.getString("userName");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    boolean role = rs.getBoolean("role");
                    int status = rs.getInt("status");
                    String token = rs.getString("token");
                    String image_path = rs.getString("image_path");
                    result = new RecipeOrganizeDTO(userID, userName, password, fullName, phone, status, role, token, email, image_path);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public static void changePassword(String username, String newPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE account SET password = ? WHERE userName = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, username);
            stm.executeUpdate();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<RecipeOrganizeDTO> getAllCategories() throws ClassNotFoundException, SQLException {
        ArrayList<RecipeOrganizeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT categoryID, categoryName\n"
                        + "FROM category";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int categoryID = rs.getInt(1);
                        String categoryName = rs.getString(2);
                        RecipeOrganizeDTO o = new RecipeOrganizeDTO(categoryID, categoryName);
                        list.add(o);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<RecipeOrganizeDTO> getRecipeByCategory(String categoryID) throws ClassNotFoundException, SQLException {
        List<RecipeOrganizeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM recipe WHERE categoryID = ?";
            con = new DBUtils().getConnection();
            stm = con.prepareCall(query);
            stm.setString(1, categoryID);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RecipeOrganizeDTO(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;

    }

    public static boolean searchAccount(String email)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From account "
                        + "Where email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

//        public static RecipeOrganizeDTO searchAccount(String fullName, String phone, String email)
//            throws ClassNotFoundException, SQLException {
//        RecipeOrganizeDTO result = null;
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBUtils.getConnection();
//            if (con != null) {
//                String sql = "Select * "
//                        + "From account "
//                        + "Where fullName = ? or phone = ? or email = ? ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, fullName);
//                stm.setString(2, phone);
//                stm.setString(3, email);
//
//                rs = stm.executeQuery();
//
//                if (rs.next()) {
//                    result = new RecipeOrganizeDTO(fullName, phone, email);
//                }
//            }
//
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//
//        return result;
//    }
    public static boolean updateProfileAccount(String userName, String fullName, String phone, String email)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE  account "
                        + "SET fullName = ? , phone = ? , email = ? "
                        + "WHERE userName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(4, userName);
                stm.setString(1, fullName);
                stm.setString(2, phone);
                stm.setString(3, email);

                int rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public static boolean updatePhotoProfile(String userName, String image_path)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE  account "
                        + "SET image_path = ? "
                        + "WHERE userName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(2, userName);
                stm.setString(1, image_path);
                int rs = stm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public RecipeOrganizeDTO getProfile(String userName) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account WHERE userName = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                rs = stm.executeQuery();

                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    boolean role = rs.getBoolean("role");
                    int status = rs.getInt("status");
                    String token = rs.getString("token");
                    String email = rs.getString("email");
                    String image_path = rs.getString("image_path");
                    result = new RecipeOrganizeDTO(userID, userName, password, fullName, phone, status, role, token, email, image_path);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public List<RecipeOrganizeDTO> getMealPlan(int userID) throws ClassNotFoundException, SQLException {
        List<RecipeOrganizeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT mealPlan.planID, mealPlan.planDate, mealPlan.userID, recipe.recipeID AS recipeID, recipe.recipeName, recipe.caloRecipe, recipe.description, recipe.imgUrl, recipe.avgRating, recipe.difficulty, recipe.ingredient_table, recipe.categoryID "
                        + "FROM mealPlan "
                        + "JOIN recipe ON mealPlan.recipeID = recipe.recipeID "
                        + "WHERE mealPlan.userID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    list.add(new RecipeOrganizeDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public boolean addMealPlan(String planDate, int userID, int recipeID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO mealPlan (planDate, userID, recipeID) VALUES (?, ?, ?)";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, planDate);
                stm.setInt(2, userID);
                stm.setInt(3, recipeID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    public void updateMealPlan(int planID, String planDate, int recipeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE mealPlan SET recipeID = ?, planDate = ? WHERE planID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, recipeID);
                stm.setString(2, planDate);
                stm.setInt(3, planID);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void deleteMealPlan(int planId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM mealPlan WHERE planId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, planId);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public ArrayList<RecipeOrganizeDTO> getManagerAccount() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;
        ArrayList<RecipeOrganizeDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM account";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int userID = rs.getInt("userID");
                        String userName = rs.getString("userName");
                        String password = rs.getString("password");
                        String fullName = rs.getString("fullName");
                        String phone = rs.getString("phone");
                        boolean role = rs.getBoolean("role");
                        int status = rs.getInt("status");
                        String token = rs.getString("token");
                        String email = rs.getString("email");
                        String image_path = rs.getString("image_path");
                        result = new RecipeOrganizeDTO(userID, userName, password, fullName, phone, status, role, token, email, image_path);
                        list.add(result);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return list;
    }

    public boolean saveComment(String userName, String recipeID, String comment, Timestamp feedbackDate) throws SQLException, ClassNotFoundException {
        boolean success = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Get database connection
            con = DBUtils.getConnection();

            // Create SQL query with parameters
            String sql = "INSERT INTO feedback (userName, recipeID, comment, feedbackDate) VALUES (?, ?, ?, ?)";

            // Create PreparedStatement object from SQL query
            ps = con.prepareStatement(sql);

            // Set values for the parameters
            ps.setString(1, userName);
            ps.setString(2, recipeID);
            ps.setString(3, comment);
            ps.setTimestamp(4, feedbackDate);

            // Execute the query and get the result
            int result = ps.executeUpdate();

            // Check the result
            if (result > 0) {
                // Query executed successfully
                success = true;
            }
        } finally {
            // Close the resources
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
        return success;

    }

    public List<RecipeOrganizeDTO> getCommentsByProductId(String recipeID) throws SQLException {
        List<RecipeOrganizeDTO> comments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT * FROM feedback WHERE recipeID = ?");
            ps.setString(1, recipeID); // Gán giá trị cho tham số truy vấn
            rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackID = rs.getInt("feedbackID");
                String userName = rs.getString("userName");
                String comment = rs.getString("comment");
                Timestamp feedbackDate = rs.getTimestamp("feedbackDate");
                RecipeOrganizeDTO feedback = new RecipeOrganizeDTO(feedbackID, userName, comment, feedbackDate);
                comments.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return comments;
    }

    public List<RecipeDTO> searchRecipeID(String recipeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<RecipeDTO> listP = new ArrayList<>();
        int id = Integer.parseInt(recipeID);
        try {
            String query = "SELECT *\n"
                    + "FROM recipe\n"
                    + "WHERE  recipeID like ?  ";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                RecipeDTO o = new RecipeDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8)
                );
                listP.add(o);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listP;
    }

    public List<StepsDTO> stepByRecipeID(String recipeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<StepsDTO> list = new ArrayList<>();
        int id = Integer.parseInt(recipeID);
        try {
            String query = "SELECT *\n"
                    + "FROM step\n"
                    + "WHERE  recipeID like ?  ";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                StepsDTO o = new StepsDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                list.add(o);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public static boolean updateAccountStatus(String userName, int i) throws ClassNotFoundException, SQLException {
        boolean kq = false;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RecipeOrganizeDTO result = null;
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE account SET status = ? WHERE userName = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, i);
                stm.setString(2, userName);
                rs = stm.executeQuery();
                kq = true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            return kq;
        }
    }

    public boolean CreateRecipe(String recipeName, String caloRecipe, String description, String imgUrl, String difficulty, String ingredient_table, String categoryID, List<String> steps) throws SQLException, ClassNotFoundException {
        boolean recipeSuccess = false;
        boolean stepSuccess = false;
        Connection con = null;
        PreparedStatement psRecipe = null;
        PreparedStatement psStep = null;
        ResultSet rsRecipe = null;

        try {
            // Get database connection
            con = DBUtils.getConnection();

            // Create SQL query for recipe insertion
            String recipeSql = "INSERT INTO recipe (recipeName, caloRecipe, description, imgUrl, difficulty, ingredient_table, categoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Create PreparedStatement for recipe insertion
            psRecipe = con.prepareStatement(recipeSql, Statement.RETURN_GENERATED_KEYS);

            // Set values for recipe parameters
            psRecipe.setString(1, recipeName);
            psRecipe.setString(2, caloRecipe);
            psRecipe.setString(3, description);
            psRecipe.setString(4, imgUrl);
            psRecipe.setString(5, difficulty);
            psRecipe.setString(6, ingredient_table);
            psRecipe.setString(7, categoryID);

            // Execute recipe insertion query and get the generated keys
            int recipeResult = psRecipe.executeUpdate();
            rsRecipe = psRecipe.getGeneratedKeys();

            // Check if recipe insertion was successful
            if (recipeResult > 0 && rsRecipe.next()) {
                // Recipe insertion successful
                recipeSuccess = true;
            }

            // Check if steps are provided
            if (!steps.isEmpty()) {
                // Create SQL query for step insertion
                String stepSql = "INSERT INTO step (recipeID, descriptionName) VALUES (?, ?)";

                // Create PreparedStatement for step insertion
                psStep = con.prepareStatement(stepSql);

                // Set recipeID and stepDescription values for each step
                int recipeID = rsRecipe.getInt(1);
                for (String step : steps) {
                    psStep.setInt(1, recipeID);
                    psStep.setString(2, step);
                    psStep.addBatch();
                }

                // Execute step insertion batch
                int[] stepResults = psStep.executeBatch();

                // Check if all steps were inserted successfully
                boolean allStepsInserted = true;
                for (int stepResult : stepResults) {
                    if (stepResult != PreparedStatement.SUCCESS_NO_INFO) {
                        allStepsInserted = false;
                        break;
                    }
                }

                // Set step success flag
                stepSuccess = allStepsInserted;
            } else {
                // No steps provided, consider step insertion as successful
                stepSuccess = true;
            }
        } finally {
            // Close the resources
            if (rsRecipe != null) {
                rsRecipe.close();
            }
            if (psRecipe != null) {
                psRecipe.close();
            }
            if (psStep != null) {
                psStep.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return recipeSuccess || stepSuccess;
    }

    public void updateRecipe(String recipeName, String caloRecipe, String description, String imgUrl, String difficulty, String ingredient_table, String categoryID, String recipeID, List<String> steps) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement psRecipe = null;
        PreparedStatement psStepDelete = null;
        PreparedStatement psStepInsert = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // Update recipe
                String recipeSql = "UPDATE recipe\n"
                        + "SET recipeName = ?, caloRecipe = ?, description = ?, imgUrl = ?, difficulty = ?, ingredient_table = ?, categoryID = ?\n"
                        + "WHERE recipeID = ?;";
                psRecipe = con.prepareStatement(recipeSql);
                psRecipe.setString(1, recipeName);
                psRecipe.setString(2, caloRecipe);
                psRecipe.setString(3, description);
                psRecipe.setString(4, imgUrl);
                psRecipe.setString(5, difficulty);
                psRecipe.setString(6, ingredient_table);
                psRecipe.setString(7, categoryID);
                psRecipe.setString(8, recipeID);
                psRecipe.executeUpdate();

                // Delete existing steps
                String stepDeleteSql = "DELETE FROM step WHERE recipeID = ?;";
                psStepDelete = con.prepareStatement(stepDeleteSql);
                psStepDelete.setString(1, recipeID);
                psStepDelete.executeUpdate();

                // Insert new steps
                String stepInsertSql = "INSERT INTO step (recipeID, descriptionName) VALUES (?, ?);";
                psStepInsert = con.prepareStatement(stepInsertSql);
                for (String step : steps) {
                    psStepInsert.setString(1, recipeID);
                    psStepInsert.setString(2, step);
                    psStepInsert.executeUpdate();
                }
            }
        } finally {
            if (psRecipe != null) {
                psRecipe.close();
            }
            if (psStepDelete != null) {
                psStepDelete.close();
            }
            if (psStepInsert != null) {
                psStepInsert.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<RecipeOrganizeDTO> deleteRecipe(String recipeID) throws SQLException, ClassNotFoundException {
        String sqlDeleteFeedback = "DELETE FROM feedback WHERE recipeID = ?";
        String sqlDeleteFavorite = "DELETE FROM favorite WHERE recipeID = ?";
        String sqlDeleteStep = "DELETE FROM step WHERE recipeID = ?";
        String sqlDeleteRecipe = "DELETE FROM recipe WHERE recipeID = ?";

        List<RecipeOrganizeDTO> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statementDeleteFeedback = null;
        PreparedStatement statementDeleteFavorite = null;
        PreparedStatement statementDeleteStep = null;
        PreparedStatement statementDeleteRecipe = null;

        try {
            connection = DBUtils.getConnection();

            // Bắt đầu một transaction
            connection.setAutoCommit(false);

            // Xóa các bản ghi liên quan trong bảng feedback
            statementDeleteFeedback = connection.prepareStatement(sqlDeleteFeedback);
            statementDeleteFeedback.setString(1, recipeID);
            statementDeleteFeedback.executeUpdate();

            // Xóa các bản ghi liên quan trong bảng favorite
            statementDeleteFavorite = connection.prepareStatement(sqlDeleteFavorite);
            statementDeleteFavorite.setString(1, recipeID);
            statementDeleteFavorite.executeUpdate();
            // Xóa các bản ghi liên quan trong bảng step
            statementDeleteStep = connection.prepareStatement(sqlDeleteStep);
            statementDeleteStep.setString(1, recipeID);
            statementDeleteStep.executeUpdate();

            // Xóa bản ghi trong bảng recipe
            statementDeleteRecipe = connection.prepareStatement(sqlDeleteRecipe);
            statementDeleteRecipe.setString(1, recipeID);
            statementDeleteRecipe.executeUpdate();

            // Commit transaction
            connection.commit();
        } catch (SQLException e) {
            // Rollback transaction nếu có lỗi xảy ra
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            // Thiết lập lại chế độ tự động commit
            if (connection != null) {
                connection.setAutoCommit(true);
            }

            // Đóng các PreparedStatement và Connection
            if (statementDeleteFeedback != null) {
                statementDeleteFeedback.close();
            }
            if (statementDeleteFavorite != null) {
                statementDeleteFavorite.close();
            }
            if (statementDeleteStep != null) {
                statementDeleteStep.close();
            }
            if (statementDeleteRecipe != null) {
                statementDeleteRecipe.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public String getCategoryByRecipeID(int recipeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String categoryID = null;
        try {
            String query = "SELECT categoryID FROM recipe WHERE recipeID = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, recipeID);
            rs = stm.executeQuery();
            if (rs.next()) {
                categoryID = String.valueOf(rs.getInt("categoryID"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return categoryID;
    }

    public void addIndividualCalory(int userID, float calory)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            List<RecipeOrganizeDTO> mealplan = getMealPlan(userID);
            if (mealplan.isEmpty()) {
                String query = "INSERT into individual_calory(userID, calo)"
                        + " Values(?, ?)";
                stm = con.prepareStatement(query);
                stm.setInt(1, userID);
                stm.setFloat(2, calory);
                stm.executeUpdate();
            } else {
                if (calory >= 1500 && calory <= 4000) {
                    String query = "UPDATE individual_calory SET calo = ? WHERE userID = ?";
                    stm = con.prepareStatement(query);
                    stm.setFloat(1, calory);
                    stm.setInt(2, userID);
                    stm.executeUpdate();
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public float getIndividualCalory(int userID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        float calo = 0;
        try {
            String query = "SELECT * FROM individual_calory WHERE userID = ?";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, userID);
            rs = stm.executeQuery();
            if (rs.next()) {
                calo = rs.getFloat("calo");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return calo;
    }

    public List<RecipeOrganizeDTO> getAllMealPlanToSendEmailAutomatically() throws SQLException, ClassNotFoundException {
        List<RecipeOrganizeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT mealPlan.userID, account.email, mealPlan.planDate, recipe.recipeID, recipe.recipeName, recipe.caloRecipe, recipe.imgUrl "
                    + "FROM mealPlan "
                    + "JOIN recipe ON mealPlan.recipeID = recipe.recipeID "
                    + "JOIN account ON mealPlan.userID = account.userID "
                    + "ORDER BY mealPlan.userID";
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                RecipeOrganizeDTO dto = new RecipeOrganizeDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getString(7));
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<RecipeOrganizeDTO> getRecipesCalories(int minCalories, int maxCalories) throws ClassNotFoundException {
        List<RecipeOrganizeDTO> recipes = new ArrayList<>();

        // Assuming you have access to a recipe data source (e.g., database)
        // Perform the necessary query to retrieve recipes based on category and calorie range
        // Replace the following code with your actual data retrieval logic
        // Example using a database query
        String query = "SELECT * FROM recipe WHERE caloRecipe >= ? AND caloRecipe <= ?";
        try (Connection connection = DBUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, minCalories);
            statement.setInt(2, maxCalories);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RecipeOrganizeDTO recipe = new RecipeOrganizeDTO();
                // Map the retrieved data to the Recipe object
                recipe.setRecipeID(resultSet.getInt("recipeID"));
                recipe.setRecipeName(resultSet.getString("recipeName"));
                recipe.setCaloRecipe(resultSet.getInt("caloRecipe"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImgUrl(resultSet.getString("imgUrl"));
                // Set other properties as needed
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that occur during data retrieval
        }

        return recipes;
    }
    
    public boolean AddFavorite(int favoriteID, int userID, int recipeID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO favorite(favoriteID, userID, recipeID) "
                        + "Values (?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, favoriteID);
                stm.setInt(2, userID);
                stm.setInt(3, recipeID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    private List<FavoriteDTO> listFavorite;

    public List<FavoriteDTO> getListFavorite() {
        return listFavorite;
    }

    public void AllFavorite(int userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select recipe.recipeID, recipeName, imgUrl "
                        + "From recipe "
                        + "JOIN favorite "
                        + "ON recipe.recipeID = favorite.recipeID "
                        + "Where favorite.userID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int recipeID = rs.getInt("recipeID");
                    String recipeName = rs.getString("recipeName");
                    String imgUrl = rs.getString("imgUrl");

                    FavoriteDTO dto = new FavoriteDTO(recipeID, recipeName, imgUrl);
                    if (this.listFavorite == null) {
                        this.listFavorite = new ArrayList<>();
                    }
                    this.listFavorite.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean checkFavorite(int recipeID, int userID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select recipeID "
                        + "From favorite "
                        + "Where userID = ? "
                        + "AND recipeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setInt(2, recipeID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean RemoveFavorite(int recipeID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Delete from favorite "
                        + "Where recipeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, recipeID);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean checkRating(int recipeID, String userName)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select recipeID From rating Where userName = ? AND recipeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, userName);
                stm.setInt(2, recipeID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean addRating(int ratingID, String userName, int recipeID, float ratingValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO rating(ratingID, userName, recipeID, ratingValue) Values (?, ?, ?, ?) ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, ratingID);
                stm.setString(2, userName);
                stm.setInt(3, recipeID);
                stm.setFloat(4, ratingValue);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRating(String userName, int recipeID, float ratingValue)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE rating Set ratingValue = ? WHERE userName = ? and recipeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setFloat(1, ratingValue);
                stm.setString(2, userName);
                stm.setInt(3, recipeID);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    private List<ShowRatingDTO> listRating;

    public List<ShowRatingDTO> getListRating() {
        return listRating;
    }

    public void ShowRating(int recipeID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT recipeID, COUNT(ratingID) AS totalRating, AVG(ratingValue) AS averageRating "
                        + "FROM rating\n "
                        + "where recipeID = ? \n"
                        + "GROUP BY recipeID ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, recipeID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int ratingCount = rs.getInt("totalRating");
                    float averageRating = rs.getFloat("averageRating");

                    ShowRatingDTO dto = new ShowRatingDTO(recipeID, ratingCount, averageRating);

                    if (this.listRating == null) {
                        this.listRating = new ArrayList<>();
                    }
                    this.listRating.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
    
    public void updateRatingRecipe(int avgRating, int recipeID)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE recipe Set avgRating = ? WHERE recipeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, avgRating);
                stm.setInt(2, recipeID);

                int row = stm.executeUpdate();

                if (row > 0) {
                    
                }
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}

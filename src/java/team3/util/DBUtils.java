/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team3.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author THIS PC
 */
public class DBUtils implements Serializable {
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver:"
                + "//localhost:1433"
                + ";databaseName=RecipeOrganize";
        Connection con = DriverManager.getConnection(url, "sa", "1234567890");
        return con;
    }
    
}

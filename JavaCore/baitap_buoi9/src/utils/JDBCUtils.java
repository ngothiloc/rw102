package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static Connection connection;

    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/rw102";
        String username = "root";
        String password = "311004";
        Connection conn = null;
        try {
            return  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } return conn;
    }

    public static void closeConnection(){
        try {
            if ( connection != null) {
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

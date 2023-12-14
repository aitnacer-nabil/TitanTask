package org.titans.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String username ="root";
    private static final  String password = "root";
    private static final  String url ="jdbc:mysql://localhost:3306/gestion";
    private static Connection connection = null;

    public static Connection getConnectionDB() {
        if(connection == null){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error In DB");
                throw new RuntimeException(e);

            }

        }
        return  connection;
    }
}

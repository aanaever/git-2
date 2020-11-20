package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/user?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME= "root";
    private static final String PASSWORD = "1111";
    private  Connection connection;
    public Connection getConnection(){
        try{
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection OOOK");
    } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERRROR!!!!");
        }
        return connection;
        }
}

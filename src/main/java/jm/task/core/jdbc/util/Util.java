package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME= "root";
    private static final String PASSWORD = "1111";

    public static Connection getConnection(){
        Connection connection = null;
        try{
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection OK");
    } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection Error!");
        }
        return connection;
        }

        public static SessionFactory getSessionFactory(){
            Properties properties = new Properties();
            properties.setProperty(Environment.URL,"jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "1111");
            properties.setProperty(Environment.DRIVER,"com.mysql.jdbc.Driver");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO,"create");
            properties.setProperty(Environment.SHOW_SQL,"true");
            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            SessionFactory sessionFactory;
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Connection hibernate ok");
            return sessionFactory;
        }
}

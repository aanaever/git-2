package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Madara", "Uchiha", (byte) 112);
        user.saveUser("Itachi", "Uchiha", (byte) 21);
        user.saveUser("Sasuke", "Uchiha", (byte) 16);
        user.saveUser("Sarada", "Uchiha", (byte) 12);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}

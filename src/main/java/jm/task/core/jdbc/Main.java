package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        /*UserDao user = new UserDaoJDBCImpl();
        user.createUsersTable();
        user.saveUser("Madara", "Uchiha", (byte) 112);
        user.saveUser("Itachi", "Uchiha", (byte) 21);
        user.saveUser("Sasuke", "Uchiha", (byte) 16);
        user.saveUser("Sarada", "Uchiha", (byte) 12);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();

         */
        /*Util util = new Util();
        util.getSessionFactory();*/

        UserDaoHibernateImpl user = new UserDaoHibernateImpl();
        user.createUsersTable();
        user.saveUser("A", "S", (byte) 11);
        user.saveUser("D", "J", (byte) 22);
        user.saveUser("C", "B", (byte) 13);
        user.saveUser("V", "P", (byte) 10);
        System.out.println(user.getAllUsers());
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}

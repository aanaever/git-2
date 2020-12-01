package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(" CREATE TABLE IF NOT EXISTS user (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT NOT NULL," +
                    "PRIMARY KEY (`id`))").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User removeUserBy = (User) session.get(User.class, id);
            session.delete(removeUserBy);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}

package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            connection.createStatement().execute(" CREATE TABLE IF NOT EXISTS user (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT NOT NULL," +
                    "PRIMARY KEY (`id`))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            connection.createStatement().execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            String saveUsers = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveUsers);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            String removeUserBy = "DELETE FROM user WHERE (`id` = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(removeUserBy);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2),
                        resultSet.getString(3),
                        (byte)resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            connection.createStatement().execute("TRUNCATE TABLE user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

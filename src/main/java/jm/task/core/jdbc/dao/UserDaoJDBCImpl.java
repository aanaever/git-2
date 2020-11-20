package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {
        Util util = new Util();
        connection = util.getConnection();
    }

    @Override
    public void createUsersTable() {
        try {
            String createTab = " CREATE TABLE `user`.`users` (" +
                    "`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT NOT NULL," +
                    "PRIMARY KEY (`id`))";
            connection.createStatement().execute(createTab);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            String dropUser = "DROP TABLE IF EXISTS `user`.`users`";
            connection.createStatement().execute(dropUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            String saveUsers = "INSERT INTO `user`.`users` SET name = ?, lastName = ?, age = ?";
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
            String removeUserBy = "DELETE FROM `user`.`users` WHERE `id` = ?";
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
            String listAll = "SELECT * FROM `user`.`users`";
            ResultSet resultSet = connection.createStatement().executeQuery(listAll);
            while (resultSet.next()) {
                list.add(new User(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            String clear = "TRUNCATE `user`.`users`";
            connection.createStatement().execute(clear);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

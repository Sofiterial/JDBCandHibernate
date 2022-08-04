package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.start()) {
            String SQL = "CREATE TABLE IF NOT EXISTS solur(" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR NOT NULL," +
                    "lastName VARCHAR NOT NULL," +
                    "age SMALLINT NOT NULL CHECK (age>0)" +
                    ")";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.start()) {
            String SQL = "DROP TABLE IF EXISTS solur";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.start()) {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO solur (name, lastName, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.start()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM solur WHERE  id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.start();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM solur")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            return userList;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void cleanUsersTable() {
        try (Connection connection = Util.start();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM solur")) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}

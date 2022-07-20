package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Connection connection = Util.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                statement.executeUpdate("create table users (id int not null auto_increment, firstName varchar(255), lastName varchar(255), age int, primary key (id))");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                statement.executeUpdate("drop table users");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("insert into users (firstName, lastName, age) value(?, ?, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?")) {
                preparedStatement.setInt(1, (int) id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection()) {
            try(Statement statement = connection.createStatement();) {
                statement.executeUpdate("truncate table users");
                connection.commit();
            }catch (SQLException e) {
                connection.rollback();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}

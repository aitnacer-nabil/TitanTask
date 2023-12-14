package org.titans.dao.impl;

import org.titans.entities.*;
import org.titans.dao.UserDao;
import org.titans.util.ConnectionDB;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
Connection connection;

    public UserDaoImpl() {
        connection = ConnectionDB.getConnectionDB();
    }

    @Override
    public void createUser(User user) {

        try (

             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO user (user_id, user_name, user_email, user_role, user_password) VALUES (?, ?, ?, ?, ?)"
             )) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().name());
            preparedStatement.setString(5, user.getPassword());

           int i = preparedStatement.executeUpdate();
           if(i == 1){
               System.out.println("User Successfully Added");
           } else {
               System.out.println("User Not Added");
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(String userId) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM user WHERE user_id = ?"
             )) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user")) {

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void updateUser(String id,  User user) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE user SET user_name = ?, user_email = ?, user_role = ?, user_password = ? WHERE user_id = ?"
             )) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, id);

            if(preparedStatement.executeUpdate() == 1 ){
                System.out.println("User Updated");
            } else {
                System.out.println("User Not Updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String userId) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM user WHERE user_id = ?"
             )) {
            preparedStatement.setString(1, userId);

            if(preparedStatement.executeUpdate() == 1 ){
                System.out.println("User Deleted");
            } else {
                System.out.println("User Not Deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("user_id");
        String username = resultSet.getString("user_name");
        String email = resultSet.getString("user_email");
        String roleString = resultSet.getString("user_role");
        Role role = Role.valueOf(roleString);
        String password = resultSet.getString("user_password");

        return new User(id, username, email, password, role);
    }
}
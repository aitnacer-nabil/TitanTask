package org.titans.services;

import org.titans.entities.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

    void updateUser(String id ,User user);

    void deleteUser(String userId);
}
package org.titans.dao;

import org.titans.entities.Task;
import org.titans.entities.User;

import java.util.List;

public interface UserDao {
    int createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

    int updateUser(String id ,User user);

    int deleteUser(String userId);
    User getUserByEmail(String email);

}
package org.titans.repositories;

import org.titans.dao.impl.UserDaoImpl;
import org.titans.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    UserDaoImpl userDAOImpl = new UserDaoImpl();

    public UserRepository() {
        this.listUsers = userDAOImpl.getAllUsers();
    }

    List<User> listUsers ;

    void createUser(User user){
        userDAOImpl.createUser(user);
    }

    User getUserById(String userId){
        User user = new User();
        return user;}

    List<User> getAllUsers(){
        return listUsers;}

    void updateUser(String id ,User user){
        userDAOImpl.updateUser(id,user);
    }

    void deleteUser(String userId){
        userDAOImpl.deleteUser(userId);
        listUsers.stream().filter(user -> user.getId().equals(userId)).findFirst().ifPresent(listUsers::remove);
    }
}

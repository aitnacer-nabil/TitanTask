package org.titans.repositories;

import org.titans.dao.impl.UserDaoImpl;
import org.titans.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UserRepository {
    UserDaoImpl userDAOImpl = new UserDaoImpl();
    List<User> listUsers;

    public UserRepository() {
        this.listUsers = userDAOImpl.getAllUsers();
    }

    public void createUser(User user) {

        if(userDAOImpl.createUser(user) == 1){
            listUsers.add(user);
        };
    }

    public User getUserById(String userId) {
        if (listUsers.isEmpty()) {
            listUsers = userDAOImpl.getAllUsers();
        }
        return listUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .get();

    }

    public List<User> getAllUsers() {
        return listUsers;
    }

    public void updateUser(String id, User user) throws Exception {
        if (userDAOImpl.updateUser(id, user) == 1) {
            int index = IntStream.range(0,listUsers.size())
                    .filter(i -> listUsers.get(i).getId().equals(id))
                    .findFirst()
                    .orElseThrow(Exception::new);
            listUsers.set(index,user);
        }
        ;
    }

    public void deleteUser(String userId) {
        if (userDAOImpl.deleteUser(userId) == 1) {
            listUsers.stream().filter(user -> user.getId().equals(userId)).findFirst().ifPresent(listUsers::remove);
        }
    }
}

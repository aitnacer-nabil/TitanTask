package org.titans.controllers;

import org.titans.entities.User;
import org.titans.repositories.UserRepository;

public class UserLogin {
    private UserRepository userRepository;

    public UserLogin() {
        userRepository = new UserRepository();
    }

    public User Login(String email,String password){
        User user = userRepository.getUserByEmail(email);
        if (  user!= null && user.getPassword().equals(password)){
            return  user;
        };
            return null;

    }
}

package com.sda.eshop.controller;

import com.sda.eshop.model.User;
import com.sda.eshop.service.AuthenticationService;
import com.sda.eshop.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;
    private AuthenticationService authService;

    public UserController(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public void save(User user) {
        userService.save(user);
    }

    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    public Boolean login(String username, String password) {
        return authService.login(username, password);
    }

}

package com.sda.eshop.controller;

import com.sda.eshop.model.User;
import com.sda.eshop.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> findAll() {
        return userService.findAll();
    }

    public void save(User user) {
        userService.save(user);
    }
}

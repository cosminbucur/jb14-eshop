package com.sda.eshop.controller;

import com.sda.eshop.model.User;
import com.sda.eshop.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService = new UserService();

    public List<User> findAll() {
        return userService.findAll();
    }
}

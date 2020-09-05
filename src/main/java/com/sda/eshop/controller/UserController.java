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

    public User findById(Long id) {
        return userService.findById(id);
    }

    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

    public User update(Long id, User userDetails) {
        return userService.update(id, userDetails);
    }

    public User update(User user) {
        return userService.update(user);
    }

    public void deleteUser(Long id) {
        userService.delete(id);
    }

    public Boolean login(String username, String password) {
        return authService.login(username, password);
    }

}

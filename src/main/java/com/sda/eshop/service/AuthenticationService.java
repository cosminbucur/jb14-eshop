package com.sda.eshop.service;

import com.sda.eshop.model.User;

public class AuthenticationService {

    private UserService userService;

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    // TODO: use the login mechanism
    public Boolean login(String username, String password) {
        // find user
        User loginUser = userService.findByUsername(username);

        if (loginUser == null) {
            System.out.println("username not found!");
        }

        // check password match
        if (loginUser.getPassword().equals(password)) {
            System.out.println("Successful authentication.");
            return true;
        } else {
            System.out.println("Username and password don't match. Try again");
            return false;
        }

    }
}

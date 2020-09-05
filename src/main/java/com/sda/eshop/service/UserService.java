package com.sda.eshop.service;

import com.sda.eshop.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User findByUsername(String username);

    User findById(Long id);
}

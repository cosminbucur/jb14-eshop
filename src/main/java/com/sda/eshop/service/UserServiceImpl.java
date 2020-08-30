package com.sda.eshop.service;

import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // must mock all used objects (with method calls)
    @Override
    public List<User> findAll() {
        log.info("Trying to find all users");

        // this works for sure (tested in another test)
        List<User> users = userDao.findAll();

        if (users.isEmpty()) {
            log.info("Failed to find users.");
            return Collections.emptyList();
        }

        return users;
    }

}

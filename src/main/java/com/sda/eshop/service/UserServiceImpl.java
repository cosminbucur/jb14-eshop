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

    @Override
    public void save(User user) {
        log.info("Trying to create a new user");

        // validate
        if (isValid(user)) {
            // prevent duplicate username
            User foundUser = findByUsername(user.getUsername());
            if (null != foundUser) {
                log.error("This username already exists. Try again!");
            } else {
                userDao.save(user);
            }
        }
    }

    private boolean isValid(User user) {
        return null != user.getName() &&
            null != user.getUsername() &&
            null != user.getPassword();
    }

    // TODO: findByUsername
    private User findByUsername(String username) {
        log.info("Trying to find user by username: {}", username);

        User user = userDao.findByUsername(username);

        if (null == user) {
            log.info("Failed to find a user with username: {}", username);
            return null;
        }

        return user;
    }


}

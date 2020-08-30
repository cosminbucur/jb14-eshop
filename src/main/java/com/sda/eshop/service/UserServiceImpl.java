package com.sda.eshop.service;

import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static com.sda.eshop.utils.Constants.USER_FAIL_FIND_BY_ID;
import static com.sda.eshop.utils.Constants.USER_FIND_ALL;
import static com.sda.eshop.utils.Constants.USER_FIND_BY_USERNAME;
import static com.sda.eshop.utils.Constants.USER_SAVE;

public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // must mock all used objects (with method calls)
    @Override
    public List<User> findAll() {
        log.info(USER_FIND_ALL);

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
        log.info(USER_SAVE);

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

    public User findByUsername(String username) {
        log.info(USER_FIND_BY_USERNAME, username);

        User user = userDao.findByUsername(username);

        if (null == user) {
            log.info(USER_FAIL_FIND_BY_ID, username);
            return null;
        }

        return user;
    }

}

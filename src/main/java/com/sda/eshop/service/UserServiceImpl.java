package com.sda.eshop.service;

import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

import static com.sda.eshop.utils.Constants.*;

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
    public User save(User user) {
        log.info(USER_SAVE);

        User result = null;

        // validate
        if (isValid(user)) {
            // prevent duplicate username

            if (userDao.usernameExists(user.getUsername())) {
                log.error(USERNAME_ALREADY_EXISTS, user.getUsername());
                return null;
            } else {
                userDao.save(user);
            }
        }
        return result;
    }

    private boolean isValid(User user) {
        return user.getName() != null &&
                user.getUsername() != null &&
                user.getPassword() != null;
    }

    @Override
    public User findById(Long id) {
        log.info(USER_FIND_BY_ID, id);

        User user = userDao.findById(id);

        if (user == null) {
            log.info(USER_FAIL_FIND_BY_ID, id);
            return null;
        }

        return user;
    }

    public User findByUsername(String username) {
        log.info(USER_FIND_BY_USERNAME, username);

        User user = userDao.findByUsername(username);

        if (user == null) {
            log.info(USER_FAIL_FIND_BY_ID, username);
            return null;
        }

        return user;
    }

    public User update(Long id, User userDetails) {
        log.info(USER_UPDATE_BY_ID, id, userDetails);
        return userDao.update(id, userDetails);
    }

    public User update(User user) {
        log.info(USER_UPDATE, user);
        return userDao.update(user);
    }

    public void delete(Long id) {
        log.info(USER_DELETE, id);
        userDao.delete(id);
    }
}

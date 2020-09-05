package com.sda.eshop.dao;

import com.sda.eshop.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    @Test
    void givenUser_whenSave_thenReturnSavedUser() {
        // given
        UserDao userdao = new UserDao();
        User user = new User();
        user.setName("Alex");
        user.setPassword("secret");
        user.setUsername("Alexutu");

        // when
        List<User> usersBefore = userdao.findAll();
        User savedUser = userdao.save(user);
        List<User> usersAfter = userdao.findAll();

        // then
        assertThat(usersAfter.size()).isGreaterThan(usersBefore.size());
        assertThat(savedUser.getUsername()).isEqualTo("Alexutu");
    }

    @Test
    void givenUser_whenUsernameExists_thenReturnTrue() {
        // given
        User user = new User();
        user.setUsername("alex");

        UserDao userDao = new UserDao();
        userDao.save(user);

        boolean expected = true;

        // when
        boolean actual = userDao.usernameExists(user.getUsername());

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenNoUser_whenUsernameExists_thenReturnFalse() {
        // given
        UserDao userDao = new UserDao();
        boolean expected = false;

        // when
        boolean actual = userDao.usernameExists("andrei");

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
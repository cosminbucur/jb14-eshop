package com.sda.eshop.dao;

import com.sda.eshop.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    @Test
    void save() {
        // given
        UserDao userdao = new UserDao();
        User user = new User();
        user.setName("Alex");
        user.setPassword("secret");
        user.setUsername("Alexutu");

        // when
        List<User> usersBefore = userdao.findAll();
        userdao.save(user);
        List<User> usersAfter = userdao.findAll();
        // then
        assertThat(usersAfter.size()).isGreaterThan(usersBefore.size());
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
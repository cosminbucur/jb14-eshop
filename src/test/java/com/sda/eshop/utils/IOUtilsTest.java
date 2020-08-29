package com.sda.eshop.utils;

import com.sda.eshop.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class IOUtilsTest {

    @Test
    void loadUsers() {
        List<User> expected = Arrays.asList(new User("Radu Chirila"));
        List<User> actual = IOUtils.loadUsers();
        assertThat(actual.get(0)).isEqualTo(expected.get(0));


    }
}
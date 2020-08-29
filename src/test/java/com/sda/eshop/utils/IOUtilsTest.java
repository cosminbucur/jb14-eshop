package com.sda.eshop.utils;

import com.sda.eshop.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IOUtilsTest {

    @Test
    void givenATextFile_whenLoadUsers_thenReturnAListOfUsers() {
        // given
        List<User> expected = Arrays.asList(new User("Radu Chirila"));

        // when
        List<User> actual = IOUtils.loadUsers();

        // then
        assertThat(actual.get(0)).isEqualTo(expected.get(0));
    }
}
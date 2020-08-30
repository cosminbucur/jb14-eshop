package com.sda.eshop.service;

import com.sda.eshop.dao.UserDao;
import com.sda.eshop.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    public UserDao userDaoMock;

    @InjectMocks
    private UserService userService;

    @Test
    void givenAUser_whenFindAll_thenReturnList() {
        // given
        User user = new User();
        user.setUsername("jon");

        List<User> expected = Collections.singletonList(user);

        // when
        Mockito.when(userDaoMock.findAll())
            .thenReturn(expected);

        List<User> actual = userService.findAll();

        // then
        // check that I have 1 item in list
        // check that actual is equal to expected
        // check that username of first item in expected is "jon"
        assertThat(actual.get(0).getUsername()).isEqualTo(expected.get(0).getUsername());
    }
}
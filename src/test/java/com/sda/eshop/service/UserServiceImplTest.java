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

// example of unit test with mocks
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    // the user dao is mocked. instead of accessing the db,
    // we force it to return a list with one object
    @Mock
    public UserDao userDaoMock;

    // mockito will instantiate the user service by using @InjectMocks
    // no need to instantiate like UserService service = new UserService();
    @InjectMocks
    private UserServiceImpl userService;

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
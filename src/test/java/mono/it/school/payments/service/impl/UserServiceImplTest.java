package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.User;
import mono.it.school.payments.repository.UserRepository;
import mono.it.school.payments.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserRepository userRepository;


    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

//    @Test
//    @MethodSource("getUsers")
//    void save_ShouldReturnUserIfUserSaved(List<User> existingUsers) {
//
//        doReturn(existingUsers).when(userRepository).getAll();
//        User user = new User("Serhii Mazur",
//                "qwerty@cmail.com",
//                "+380456123789");
//        doReturn(user).when(userService).save(user);
//        User actual = userService.save(user);
//
//    }

    @Test
//    @MethodSource("getEmptyUserList")
    void save_ShouldReturnUserIfUserSaved() {
        User user = new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789");

        doReturn(null).when(userRepository).getByEmail(user.getEmail());
        doReturn(user).when(userService).save(user);

        User actual = userService.save(user);

        assertThat(actual.getFullName()).isEqualTo("Serhii Mazur");
        assertThat(actual.getEmail()).isEqualTo("qwerty@cmail.com");
        assertThat(actual.getPhoneNumber()).isEqualTo("+380456123789");
    }
//    @Test
//    @MethodSource("getUsers")
//    void save_ShouldThrowExceptionIfUserExists(List<User> existingUsers) {
//
//    }

    private static Stream<Arguments> getEmptyUserList() {

        return Stream.of(Arguments.of(Collections.EMPTY_LIST));
    }

    private static Stream<Arguments> getSingleUser() {

        return Stream.of(Arguments.of(new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789")));
    }

    private static Stream<Arguments> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("John Doe",
                "johndoe@jmail.com",
                "+380123456789"));
        userList.add(new User("Sarah Connor",
                "sconnor@jmail.com",
                "+380987456123"));
        userList.add(new User("Sherlock Holmes",
                "sherlock@jmail.com",
                "+380654987321"));

        return Stream.of(Arguments.of(userList));
    }
}
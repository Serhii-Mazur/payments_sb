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
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void save_ShouldReturnUserIfSaved() {
        User user = new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789");

        doReturn(null).when(userRepository).getByEmail(eq("qwerty@cmail.com"));
        doReturn(user).when(userRepository).save(user);

        User actual = userService.save(user);

        verify(userRepository, times(1)).getByEmail(any());
        assertThat(actual.getFullName()).isEqualTo("Serhii Mazur");
        assertThat(actual.getEmail()).isEqualTo("qwerty@cmail.com");
        assertThat(actual.getPhoneNumber()).isEqualTo("+380456123789");
    }

    @Test
    void save_ShouldThrowExceptionIfUserExists() {
        User user = new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789");

        doReturn(user).when(userRepository).getByEmail(eq("qwerty@cmail.com"));

        UserServiceImpl.UserServiceException thrown = assertThrows(UserServiceImpl.UserServiceException.class,
                () -> userService.save(user));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void update_ShouldReturnUserIfUpdated() {
        User user = new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789");

        doReturn(user).when(userRepository).getByEmail(eq("qwerty@cmail.com"));
        doReturn(user).when(userRepository).save(user);

        User actual = userService.update(user);

        verify(userRepository, times(1)).getByEmail(any());
        assertThat(actual.getFullName()).isEqualTo("Serhii Mazur");
        assertThat(actual.getEmail()).isEqualTo("qwerty@cmail.com");
        assertThat(actual.getPhoneNumber()).isEqualTo("+380456123789");
    }

    @Test
    void update_ShouldThrowExceptionIfUserDoesNotExist() {
        User user = new User("Serhii Mazur",
                "qwerty@cmail.com",
                "+380456123789");

        doReturn(null).when(userRepository).getByEmail(eq("qwerty@cmail.com"));

        UserServiceImpl.UserServiceException thrown = assertThrows(UserServiceImpl.UserServiceException.class,
                () -> userService.update(user));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void getAll_ShouldReturnEmptyUserList() {
        List<User> emptyUserList = Collections.EMPTY_LIST;

        doReturn(emptyUserList).when(userRepository).getAll();
        List<User> actual = userService.getAll();

        verify(userRepository, times(1)).getAll();
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getUsers")
    void getAll_ShouldReturnUserList(List<User> existingUsers) {

        doReturn(existingUsers).when(userRepository).getAll();

        List<User> actual = userService.getAll();

        verify(userRepository, times(1)).getAll();
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(existingUsers.size());
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
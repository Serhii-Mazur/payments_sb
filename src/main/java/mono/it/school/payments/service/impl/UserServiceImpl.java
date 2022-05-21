package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.repository.UserRepository;
import mono.it.school.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        if (exists(user)) {
            log.warn("Attempt to add existing User {}", user);
            throw new UserServiceException(String.format("User with e-mail [%s] is already exists!", user.getEmail()));
        }

        return userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public User update(User user) {
        if (!exists(user)) {
            log.warn("Attempt to update non-existing User {}", user);
            throw new UserServiceException(String.format("User with e-mail [%s] doesn't exist!", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {

        return userRepository.getAll();
    }

    private boolean exists(User user) {
        User userFromDb = userRepository.getByEmail(user.getEmail());

        return userFromDb != null;
    }

    public class UserServiceException extends Exception {
        public UserServiceException(String message) {
            super(message);
        }
    }
}

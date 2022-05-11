package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.repository.UserRepository;
import mono.it.school.payments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User save(User user) { //TODO: Rewrite method to return boolean or User
        if (isExists(user)) {
            throw new UserServiceException("User with e-mail [" + user.getEMail() + "] is already exists!");
        }

        return userRepository.save(user);
    }

    @Override
    @SneakyThrows
    public User update(User user) {
        if (!isExists(user)) {
            throw new UserServiceException("User with e-mail [" + user.getEMail() + "] doesn't exist!");
        }
        return userRepository.save(user);
    }

    private boolean isExists(User user) {
        boolean result = false;
        for (User userFromDb : userRepository.getAll()) {
            if (user.getEMail().equals(userFromDb.getEMail())) {
                result = true;
            }
        }

        return result;
    }

    public class UserServiceException extends Exception {
        public UserServiceException() {
            super();
        }

        public UserServiceException(String message) {
            super(message);
        }

        public UserServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public UserServiceException(Throwable cause) {
            super(cause);
        }
    }
}

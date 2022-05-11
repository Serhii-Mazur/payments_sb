package mono.it.school.payments.repository.impl;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.User;
import mono.it.school.payments.entity.UserEntity;
import mono.it.school.payments.mapper.UserMapper;
import mono.it.school.payments.repository.UserRepository;
import mono.it.school.payments.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> getAll() {

        return jpaUserRepository.findAll()
                .stream()
                .map(UserMapper::entityToUser)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity savedUser = jpaUserRepository.save(UserMapper.userToEntity(user));
        log.info("User saved: ", savedUser);

        return UserMapper.entityToUser(savedUser);
    }

    @Override
    public User getById(String id) {
        UserEntity userFromDb = jpaUserRepository.getById(id);

        return UserMapper.entityToUser(userFromDb);
    }
}

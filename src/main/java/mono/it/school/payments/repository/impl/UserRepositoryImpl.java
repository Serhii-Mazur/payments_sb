package mono.it.school.payments.repository.impl;

import mono.it.school.payments.domain.User;
import mono.it.school.payments.entity.UserEntity;
import mono.it.school.payments.mapper.UserMapper;
import mono.it.school.payments.repository.UserRepository;
import mono.it.school.payments.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity savedUser = jpaUserRepository.save(UserMapper.userToEntity(user));

        return UserMapper.entityToUser(savedUser);
    }
}

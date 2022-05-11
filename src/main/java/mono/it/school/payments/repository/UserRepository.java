package mono.it.school.payments.repository;

import mono.it.school.payments.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User save(User user);

    User getById(String id);
}

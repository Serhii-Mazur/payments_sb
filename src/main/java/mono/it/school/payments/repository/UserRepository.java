package mono.it.school.payments.repository;

import mono.it.school.payments.domain.User;

public interface UserRepository {
    User save(User user);
}

package mono.it.school.payments.service;

import mono.it.school.payments.domain.User;

public interface UserService {
    User save(User user);

    User update(User user);
}

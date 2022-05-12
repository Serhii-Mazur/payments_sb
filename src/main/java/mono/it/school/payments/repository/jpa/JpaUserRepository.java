package mono.it.school.payments.repository.jpa;

import mono.it.school.payments.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, String> {

    UserEntity getByEmail(String eMail);
}

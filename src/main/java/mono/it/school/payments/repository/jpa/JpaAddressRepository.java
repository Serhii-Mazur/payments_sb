package mono.it.school.payments.repository.jpa;

import mono.it.school.payments.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaAddressRepository extends JpaRepository<AddressEntity, UUID> {

    AddressEntity findByAddress(String address);

    List<AddressEntity> findAllByUserEmail(String email);
}

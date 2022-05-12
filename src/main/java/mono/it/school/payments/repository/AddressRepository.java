package mono.it.school.payments.repository;

import mono.it.school.payments.domain.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository {
    Address save(Address address);

    List<Address> getAll();

    Address getById(UUID id);

    Address getByAddress(String address);

    List<Address> getByUserEmail(String email);
}

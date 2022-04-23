package mono.it.school.payments.repository;

import mono.it.school.payments.domain.Address;

public interface AddressRepository {
    Address save(Address address);
}

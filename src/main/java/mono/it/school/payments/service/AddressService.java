package mono.it.school.payments.service;

import mono.it.school.payments.domain.Address;

import java.util.List;

public interface AddressService {
    void save(Address address);

    Address getByAddress(String address);

    List<Address> getByUserEmail(String email);
}

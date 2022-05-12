package mono.it.school.payments.service;

import lombok.SneakyThrows;
import mono.it.school.payments.domain.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);

    @SneakyThrows
    Address update(Address address);

    List<Address> getAll();

    Address getByAddress(String address);

    List<Address> getByUserEmail(String email);
}

package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @SneakyThrows
    public Address save(Address address) {
        if (exists(address)) {
            log.warn("Attempt to add existing Address {}", address);
            throw new AddressServiceException(String.format("Address with address [%s] is already exists!", address.getAddress()));
        }

        return addressRepository.save(address);
    }

    @Override
    @SneakyThrows
    public Address update(Address address) {
        if (!exists(address)) {
            log.warn("Attempt to update non-existing Address {}", address);
            throw new AddressServiceException(String.format("Address with address [%s] does not exist!", address.getAddress()));
        }

        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {

        return addressRepository.getAll();
    }

    @Override
    public Address getByAddress(String address) {

        return addressRepository.getByAddress(address);
    }

    @Override
    public List<Address> getByUserEmail(String email) {

        return addressRepository.getByUserEmail(email);
    }

    private boolean exists(Address address) {
        boolean result = false;
        Address addressFromDb = addressRepository.getByAddress(address.getAddress());
        if (addressFromDb != null) {
            if (address.getAddressID() != null) {
                if (address.getAddressID().equals(addressFromDb.getAddressID())
                        && address.getAddress().equals(addressFromDb.getAddress())) {
                    result = true;
                }
            } else if (address.getAddress().equals(addressFromDb.getAddress())) {
                result = true;
            }
        }

        return result;
    }

    public class AddressServiceException extends Exception {
        public AddressServiceException(String message) {
            super(message);
        }
    }
}

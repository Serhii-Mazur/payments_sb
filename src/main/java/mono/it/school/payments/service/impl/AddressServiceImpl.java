package mono.it.school.payments.service.impl;

import lombok.SneakyThrows;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new AddressServiceException("Address with address [" + address.getAddress() + "] is already exists!");
        }

        return addressRepository.save(address);
    }

    @Override
    @SneakyThrows
    public Address update(Address address) {
        if (!exists(address)) {
            throw new AddressServiceException("Address with address [" + address.getAddress() + "] does not exist!");
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
        if (address.getAddressID() != null) {
            if (address.getAddressID().equals(addressFromDb.getAddressID())) {
                result = true;
            }
        }

        return result;
    }

    public class AddressServiceException extends Exception {
        public AddressServiceException() {
            super();
        }

        public AddressServiceException(String message) {
            super(message);
        }

        public AddressServiceException(String message, Throwable cause) {
            super(message, cause);
        }

        public AddressServiceException(Throwable cause) {
            super(cause);
        }
    }
}

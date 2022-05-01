package mono.it.school.payments.service.impl;

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
    public void save(Address address) { //TODO: Rewrite method to return boolean
        addressRepository.save(address);
    }

    @Override
    public Address getByAddress(String address) {

        return addressRepository.getByAddress(address);
    }

    @Override
    public List<Address> getByUserEmail(String email) {

        return addressRepository.getAllByUserEmail(email);
    }
}
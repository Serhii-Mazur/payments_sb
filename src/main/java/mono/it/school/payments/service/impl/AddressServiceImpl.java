package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;
import mono.it.school.payments.mapper.AddressMapper;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}

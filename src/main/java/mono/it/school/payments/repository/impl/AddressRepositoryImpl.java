package mono.it.school.payments.repository.impl;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;
import mono.it.school.payments.mapper.AddressMapper;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.repository.jpa.JpaAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressRepositoryImpl implements AddressRepository {

    private final JpaAddressRepository jpaAddressRepository;

    @Autowired
    public AddressRepositoryImpl(JpaAddressRepository jpaAddressRepository) {
        this.jpaAddressRepository = jpaAddressRepository;
    }

    @Override
    public Address save(Address address) {
        AddressEntity savedAddress = jpaAddressRepository.save(AddressMapper.addressToEntity(address));

        return AddressMapper.entityToAddress(savedAddress);
    }
}

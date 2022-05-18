package mono.it.school.payments.repository.impl;

import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;
import mono.it.school.payments.mapper.AddressMapper;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.repository.jpa.JpaAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
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

    @Override
    public List<Address> getAll() {

        return jpaAddressRepository.findAll()
                .stream()
                .map(AddressMapper::entityToAddress)
                .collect(Collectors.toList());
    }

    @Override
    public Address getById(UUID id) {
        AddressEntity addressFromDb = jpaAddressRepository.getById(id);

        return AddressMapper.entityToAddress(addressFromDb);
    }


    @Override
    public Address getByAddress(String address) {
        AddressEntity addressFromDb = jpaAddressRepository.findByAddress(address);

        return AddressMapper.entityToAddress(addressFromDb);
    }

    @Override
    public List<Address> getByUserEmail(String email) {

        return jpaAddressRepository.findAllByUserEmail(email)
                .stream()
                .map(AddressMapper::entityToAddress)
                .collect(Collectors.toList());
    }
}

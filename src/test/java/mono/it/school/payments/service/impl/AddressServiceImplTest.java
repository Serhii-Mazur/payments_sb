package mono.it.school.payments.service.impl;

import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private AddressRepository addressRepository;
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepository);
    }

    @Test
    void save_ShouldReturnAddressIfAddressSaved() {

    }

    @Test
    void save_ShouldThrowExceptionIfAddressExists() {

    }

    @Test
    void update_ShouldReturnAddressIfAddressUpdated() {

    }

    @Test
    void update_ShouldThrowExceptionIfAddressDoesNotExist() {

    }

    @Test
    void getByAddress_ShouldReturnAddressIfAddressExists() {

    }

    @Test
    void getByAddress_ShouldReturnNullIfAddressDoesNotExist() {

    }
}
package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.repository.AddressRepository;
import mono.it.school.payments.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
        Address addressToSave = new Address(null,
                "Address_1",
                "johndoe@jmail.com");
        Address savedAddress = new Address(UUID.fromString("5447670c-7b58-435c-b246-ac54f27c6201"),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(null).when(addressRepository).getByAddress(eq("Address_1"));
        doReturn(savedAddress).when(addressRepository).save(eq(addressToSave));

        Address actual = addressService.save(addressToSave);

        verify(addressRepository, times(1)).getByAddress(any());
        assertThat(actual.getAddress()).isEqualTo(addressToSave.getAddress());
        assertThat(actual.getUserEmail()).isEqualTo(addressToSave.getUserEmail());
    }

    @Test
    void save_ShouldThrowExceptionIfAddressExists() {
        Address addressToSave = new Address(null,
                "Address_1",
                "johndoe@jmail.com");
        Address existingAddress = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(existingAddress).when(addressRepository).getByAddress(eq("Address_1"));

        AddressServiceImpl.AddressServiceException thrown = assertThrows(AddressServiceImpl.AddressServiceException.class,
                () -> addressService.save(addressToSave));

        verify(addressRepository, times(1)).getByAddress(any());
        assertNotNull(thrown.getMessage());
    }

    @Test
    void update_ShouldReturnAddressIfAddressUpdated() {
        Address addressToUpdate = new Address(UUID.fromString("5447670c-7b58-435c-b246-ac54f27c6201"),
                "Address_1",
                "johndoe@jmail.com");
        Address existingAddress = new Address(UUID.fromString("5447670c-7b58-435c-b246-ac54f27c6201"),
                "Address_1",
                "another@jmail.com"); // Difference here

        doReturn(existingAddress).when(addressRepository).getByAddress(eq("Address_1"));
        doReturn(addressToUpdate).when(addressRepository).save(addressToUpdate);

        Address actual = addressService.update(addressToUpdate);

        verify(addressRepository, times(1)).getByAddress(any());
        assertThat(actual.getAddressID()).isEqualTo(addressToUpdate.getAddressID());
        assertThat(actual.getAddress()).isEqualTo(addressToUpdate.getAddress());
        assertThat(actual.getUserEmail()).isEqualTo(addressToUpdate.getUserEmail());
    }

    @Test
    void update_ShouldThrowExceptionIfAddressDoesNotExist() {
        Address addressToUpdate = new Address(UUID.fromString("5447670c-7b58-435c-b246-ac54f27c6201"),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(null).when(addressRepository).getByAddress(eq("Address_1"));

        AddressServiceImpl.AddressServiceException thrown = assertThrows(AddressServiceImpl.AddressServiceException.class,
                () -> addressService.update(addressToUpdate));

        verify(addressRepository, times(1)).getByAddress(any());
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getByAddress_ShouldReturnAddressIfAddressExists() {
        Address existingAddress = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(existingAddress).when(addressRepository).getByAddress(eq("Address_1"));

        Address actual = addressService.getByAddress("Address_1");

        verify(addressRepository, times(1)).getByAddress(any());
        assertThat(actual.getAddress()).isEqualTo("Address_1");
        assertThat(actual.getUserEmail()).isEqualTo("johndoe@jmail.com");
    }

    @Test
    void getByAddress_ShouldReturnNullIfAddressDoesNotExist() {

        doReturn(null).when(addressRepository).getByAddress(eq("Non-existent_Address"));

        Address actual = addressService.getByAddress("Non-existent_Address");

        verify(addressRepository, times(1)).getByAddress(any());
        assertNull(actual);
    }

    @Test
    void getAll_ShouldReturnEmptyAddressList() {
        List<Address> emptyAddressList = Collections.EMPTY_LIST;

        doReturn(emptyAddressList).when(addressRepository).getAll();
        List<Address> actual = addressService.getAll();

        verify(addressRepository, times(1)).getAll();
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getAddressList")
    void getAll_ShouldReturnAddressList(List<Address> existingAddresses) {

        doReturn(existingAddresses).when(addressRepository).getAll();

        List<Address> actual = addressService.getAll();

        verify(addressRepository, times(1)).getAll();
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(existingAddresses.size());
    }

    private static Stream<Arguments> getAddressList() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com"));
        addressList.add(new Address(UUID.randomUUID(),
                "Address_2",
                "johndoe@jmail.com"));
        addressList.add(new Address(UUID.randomUUID(),
                "Address_3",
                "sconnor@jmail.com"));

        return Stream.of(Arguments.of(addressList));
    }
}
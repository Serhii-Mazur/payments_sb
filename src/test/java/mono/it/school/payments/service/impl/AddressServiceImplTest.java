package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.domain.User;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private AddressRepository addressRepository;
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        addressRepository = Mockito.mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepository);
    }

    @ParameterizedTest
    @MethodSource("getSingleAddress")
    void save_ShouldReturnAddressIfAddressSaved(Address address) {
        Address addressWithID = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(addressWithID).when(addressRepository).getByAddress(eq("johndoe@jmail.com"));
        doReturn(addressWithID).when(addressRepository).save(eq(address));

        Address actual = addressService.save(address);

        assertThat(actual.getAddress()).isEqualTo("Address_1");
        assertThat(actual.getUserEmail()).isEqualTo("johndoe@jmail.com");
    }

    @ParameterizedTest
    @MethodSource("getSingleAddress")
    void save_ShouldThrowExceptionIfAddressExists(Address address) {
        Address addressWithID = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(addressWithID).when(addressRepository).getByAddress(eq("Address_1"));

        AddressServiceImpl.AddressServiceException thrown = assertThrows(AddressServiceImpl.AddressServiceException.class,
                () -> addressService.save(address));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void update_ShouldReturnAddressIfAddressUpdated() {
        Address addressWithID = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(addressWithID).when(addressRepository).getByAddress(eq("Address_1"));
        doReturn(addressWithID).when(addressRepository).save(addressWithID);

        Address actual = addressService.update(addressWithID);

        assertThat(actual.getAddressID()).isEqualTo(addressWithID.getAddressID());
        assertThat(actual.getAddress()).isEqualTo("Address_1");
        assertThat(actual.getUserEmail()).isEqualTo("johndoe@jmail.com");
    }

    @ParameterizedTest
    @MethodSource("getSingleAddress")
    void update_ShouldThrowExceptionIfAddressDoesNotExist(Address address) {
        Address addressWithID = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(addressWithID).when(addressRepository).getByAddress(eq("Address_1"));

        AddressServiceImpl.AddressServiceException thrown = assertThrows(AddressServiceImpl.AddressServiceException.class,
                () -> addressService.update(address));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void getByAddress_ShouldReturnAddressIfAddressExists() {
        Address address = new Address(UUID.randomUUID(),
                "Address_1",
                "johndoe@jmail.com");

        doReturn(address).when(addressRepository).getByAddress(eq("Address_1"));

        Address actual = addressService.getByAddress("Address_1");

        assertThat(actual.getAddress()).isEqualTo("Address_1");
        assertThat(actual.getUserEmail()).isEqualTo("johndoe@jmail.com");
    }

    @Test
    void getByAddress_ShouldReturnNullIfAddressDoesNotExist() {

        doReturn(null).when(addressRepository).getByAddress(eq("Non-existent_Address"));

        Address actual = addressService.getByAddress("Non-existent_Address");

        assertNull(actual);
    }

    @ParameterizedTest
    @MethodSource("getEmptyAddressList")
    void getAll_ShouldReturnEmptyAddressList(List<User> emptyAddressList) {

        doReturn(emptyAddressList).when(addressRepository).getAll();
        List<Address> actual = addressService.getAll();

        assertThat(actual.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getAddressList")
    void getAll_ShouldReturnAddressList(List<Address> existingAddresses) {

        doReturn(existingAddresses).when(addressRepository).getAll();

        List<Address> actual = addressService.getAll();

        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(existingAddresses.size());
        assertThat(actual.size()).isEqualTo(3);
    }

    private static Stream<Arguments> getEmptyAddressList() {

        return Stream.of(Arguments.of(Collections.EMPTY_LIST));
    }

    private static Stream<Arguments> getSingleAddress() {
        Address address = new Address(null,
                "Address_1",
                "johndoe@jmail.com");

        return Stream.of(Arguments.of(address));
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
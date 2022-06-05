package mono.it.school.payments.mapper;

import mono.it.school.payments.api.dto.AddressDto;
import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;

import java.util.UUID;

public class AddressMapper {

    public static AddressEntity addressToEntity(Address address) {
        AddressEntity addressEntity;
        if (address == null) {
            addressEntity = null;
        } else {
            if (address.getAddressID() == null) {
                addressEntity = new AddressEntity(UUID.randomUUID(), address.getAddress(), address.getUserEmail());
            } else {
                addressEntity = new AddressEntity(address.getAddressID(), address.getAddress(), address.getUserEmail());
            }

        }

        return addressEntity;
    }

    public static Address entityToAddress(AddressEntity addressEntity) {
        Address address;
        if (addressEntity == null) {
            address = null;
        } else {
            address = new Address(addressEntity.getAddressID(), addressEntity.getAddress(), addressEntity.getUserEmail());
        }

        return address;
    }

    public static Address dtoToAddress(AddressDto addressDto) {
        Address address;
        if (addressDto == null) {
            address = null;
        } else {
            address = new Address(addressDto.getAddressID(), addressDto.getAddress(), addressDto.getUserEmail());
        }

        return address;
    }

    public static AddressDto addressToDto(Address address) {
        AddressDto addressDto;
        if (address == null) {
            addressDto = null;
        } else {
            addressDto = new AddressDto(address.getAddressID(), address.getAddress(), address.getUserEmail());
        }

        return addressDto;
    }
}

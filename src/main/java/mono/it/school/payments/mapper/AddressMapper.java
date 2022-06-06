package mono.it.school.payments.mapper;

import mono.it.school.payments.api.dto.ResponseAddressDto;
import mono.it.school.payments.api.dto.RequestAddressDto;
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

    public static Address requestDtoToAddress(RequestAddressDto requestAddressDto) {
        Address address;
        if (requestAddressDto == null) {
            address = null;
        } else {
            address = new Address(requestAddressDto.getAddress(), requestAddressDto.getUserEmail());
        }

        return address;
    }

    public static ResponseAddressDto addressToResponseDto(Address address) {
        ResponseAddressDto responseAddressDto;
        if (address == null) {
            responseAddressDto = null;
        } else {
            responseAddressDto = new ResponseAddressDto(address.getAddressID(), address.getAddress(), address.getUserEmail());
        }

        return responseAddressDto;
    }
}

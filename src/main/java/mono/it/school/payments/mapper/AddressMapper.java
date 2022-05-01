package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;

import java.util.UUID;

public class AddressMapper {

    public static AddressEntity addressToEntity(Address address) {
        AddressEntity addressEntity;
        if (address.getAddressID() == null) {
            addressEntity = new AddressEntity(UUID.randomUUID(), address.getAddress(), address.getUserEmail());
        } else {
            addressEntity = new AddressEntity(address.getAddressID(), address.getAddress(), address.getUserEmail());
        }

        return addressEntity;
    }

    public static Address entityToAddress(AddressEntity addressEntity) {

        return new Address(addressEntity.getAddressID(), addressEntity.getAddress(), addressEntity.getUserEmail());
    }
}

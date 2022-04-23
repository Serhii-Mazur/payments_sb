package mono.it.school.payments.mapper;

import mono.it.school.payments.domain.Address;
import mono.it.school.payments.entity.AddressEntity;

public class AddressMapper {

    public static AddressEntity addressToEntity(Address address) {

        return new AddressEntity(address.getAddressID(), address.getAddress(), address.getUserEmail());
    }

    public static Address entityToAddress(AddressEntity addressEntity) {

        return new Address(addressEntity.getAddressID(), addressEntity.getAddress(), addressEntity.getUserEmail());
    }
}

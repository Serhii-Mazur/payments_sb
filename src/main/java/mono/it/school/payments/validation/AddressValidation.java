package mono.it.school.payments.validation;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Address;

@Log4j2
public class AddressValidation extends AbstractDomainEntityValidation {

    @SneakyThrows
    public static boolean validate(Address address) {
        if (isEmpty(address.getAddress())) {
            throwValidationException(getExceptionMessage("Address"));
        }
        if (isEmpty(address.getUserEmail())) {
            throwValidationException(getExceptionMessage("E-mail"));
        }

        return true;
    }
}

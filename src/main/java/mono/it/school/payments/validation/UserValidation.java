package mono.it.school.payments.validation;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.User;

@Log4j2
public class UserValidation extends AbstractDomainEntityValidation {

    @SneakyThrows
    public static boolean validate(User user) {
        if (isEmpty(user.getEMail())) {
            throwValidationException(getExceptionMessage("E-mail"));
        }
        if (isEmpty(user.getFullName())) {
            throwValidationException(getExceptionMessage("Full name"));
        }
        if (isEmpty(user.getPhoneNumber())) {
            throwValidationException(getExceptionMessage("Phone number"));
        }
        log.info("User is valid.");

        return true;
    }
}

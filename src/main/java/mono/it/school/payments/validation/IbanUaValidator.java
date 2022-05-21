package mono.it.school.payments.validation;

import mono.it.school.payments.validation.annotation.ValidIbanUa;
import mono.it.school.payments.validation.annotation.ValidUuid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class IbanUaValidator implements ConstraintValidator<ValidIbanUa, String> {

    private final String regex = "^UA\\d{8}[A-Z0-9]{19}";


    @Override
    public void initialize(ValidIbanUa constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext context) {
        return iban.matches(this.regex);
    }
}

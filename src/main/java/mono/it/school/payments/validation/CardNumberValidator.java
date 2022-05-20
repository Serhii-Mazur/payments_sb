package mono.it.school.payments.validation;

import mono.it.school.payments.validation.annotation.ValidCardNumber;
import mono.it.school.payments.validation.annotation.ValidIbanUa;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardNumberValidator implements ConstraintValidator<ValidCardNumber, String> {

    private final String regex = "[0-9]{16}";

    @Override
    public void initialize(ValidCardNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(regex);
    }
}

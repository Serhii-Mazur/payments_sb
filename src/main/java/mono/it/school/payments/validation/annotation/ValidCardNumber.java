package mono.it.school.payments.validation.annotation;

import mono.it.school.payments.validation.CardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CardNumberValidator.class)
public @interface ValidCardNumber {
    String message() default "Invalid Card Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

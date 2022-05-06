package mono.it.school.payments.validation;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Payment;

@Log4j2
public class PaymentValidation extends AbstractDomainEntityValidation {

    @SneakyThrows
    public static boolean validate(Payment payment) {
        if (isEmpty(payment.getTemplateID().toString())) {
            throwValidationException(getExceptionMessage("Template"));
        }
        if (isEmpty(String.valueOf(payment.getCardNumber()))) {
            throwValidationException(getExceptionMessage("Card number"));
        }
        if (isEmpty(String.valueOf(payment.getPaymentAmount()))) {
            throwValidationException(getExceptionMessage("Payment amount"));
        }

        return true;
    }
}

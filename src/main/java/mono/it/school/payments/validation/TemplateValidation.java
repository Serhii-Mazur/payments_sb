package mono.it.school.payments.validation;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import mono.it.school.payments.domain.Template;

@Log4j2
public class TemplateValidation extends AbstractDomainEntityValidation {

    @SneakyThrows
    public static boolean validate(Template template) {
        if (isEmpty(template.getTemplateName())) {
            throwValidationException(getExceptionMessage("Name"));
        }
        if (isEmpty(template.getIban())) {
            throwValidationException(getExceptionMessage("IBAN"));
        }
        if (isEmpty(template.getAddressID().toString())) {
            throwValidationException(getExceptionMessage("Address"));
        }
        if (isEmpty(template.getPaymentPurpose())) {
            throwValidationException(getExceptionMessage("Purpose"));
        }
        log.info("Template is valid.");

        return true;
    }
}

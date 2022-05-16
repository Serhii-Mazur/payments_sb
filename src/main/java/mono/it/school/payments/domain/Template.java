package mono.it.school.payments.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class Template {

    private final UUID templateID;

    @NotEmpty(message = "Address[ID] may not be empty")
    private final UUID addressID;

    @NotEmpty(message = "PaymentPurpose may not be empty")
    private final String paymentPurpose;

    @NotEmpty(message = "TemplateName may not be empty")
    private final String templateName;

    @NotEmpty(message = "IBAN may not be empty")
    private final String iban;
}

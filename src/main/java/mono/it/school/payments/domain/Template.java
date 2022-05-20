package mono.it.school.payments.domain;

import lombok.*;
import mono.it.school.payments.validation.annotation.ValidIbanUa;
import mono.it.school.payments.validation.annotation.ValidUuid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Template {

    private UUID templateID;

    @ValidUuid
    private UUID addressID;

    @NotBlank(message = "PaymentPurpose must not be empty")
    private String paymentPurpose;

    @NotBlank(message = "TemplateName must not be empty")
    private String templateName;

    @ValidIbanUa
    private String iban;

    public Template(UUID addressID, String paymentPurpose, String templateName, String iban) {
        this.addressID = addressID;
        this.paymentPurpose = paymentPurpose;
        this.templateName = templateName;
        this.iban = iban;
    }
}

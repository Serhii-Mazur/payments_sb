package mono.it.school.payments.api.dto;

import lombok.*;
import mono.it.school.payments.validation.annotation.ValidIbanUa;
import mono.it.school.payments.validation.annotation.ValidUuid;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RequestTemplateDto {

    @ValidUuid
    private UUID addressID;

    @NotBlank(message = "PaymentPurpose must not be empty")
    private String paymentPurpose;

    @NotBlank(message = "TemplateName must not be empty")
    private String templateName;

    @ValidIbanUa
    private String iban;
}

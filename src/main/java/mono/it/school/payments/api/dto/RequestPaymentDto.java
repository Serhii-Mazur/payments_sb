package mono.it.school.payments.api.dto;

import lombok.*;
import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.validation.annotation.ValidCardNumber;
import mono.it.school.payments.validation.annotation.ValidUuid;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RequestPaymentDto {

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @ValidUuid
    private UUID templateID;

    @ValidCardNumber
    private String cardNumber;

    private float paymentAmount;
}

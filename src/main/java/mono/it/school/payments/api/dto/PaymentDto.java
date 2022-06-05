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
public class PaymentDto {

    private UUID paymentID;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @ValidUuid
    private UUID templateID;

    @ValidCardNumber
    private String cardNumber;

    private float paymentAmount;

    @Setter
    private PaymentStatus paymentStatus;

    private LocalDateTime createdDateTime;

    private LocalDateTime etlDateTime;

    public PaymentDto(String description, UUID templateID, String cardNumber, float paymentAmount) {
        this.description = description;
        this.templateID = templateID;
        this.cardNumber = cardNumber;
        this.paymentAmount = paymentAmount;
    }
}

package mono.it.school.payments.domain;

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
public class Payment {

    private UUID paymentID;

    @NotEmpty(message = "Description may not be empty")
    private String description;

    @ValidUuid
    private UUID templateID;

    @ValidCardNumber
    private String cardNumber;

//    @NotEmpty(message = "Amount may not be empty")
    private float paymentAmount;

    @Setter
    private PaymentStatus paymentStatus;

    private LocalDateTime createdDateTime;

    private LocalDateTime etlDateTime;

    public Payment(String description, UUID templateID, String cardNumber, float paymentAmount) {
        this.description = description;
        this.templateID = templateID;
        this.cardNumber = cardNumber;
        this.paymentAmount = paymentAmount;
    }
}

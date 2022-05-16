package mono.it.school.payments.domain;

import lombok.*;
import mono.it.school.payments.constants.PaymentStatus;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Payment {

    private final UUID paymentID;

    @Getter
    @NotEmpty(message = "Description may not be empty")
    private final String description;

    @NotEmpty(message = "Template[ID] may not be empty")
    private final UUID templateID;

    @NotEmpty(message = "CardNumber may not be empty")
    private final long cardNumber;

    @NotEmpty(message = "Amount may not be empty")
    private final float paymentAmount;

    private final PaymentStatus paymentStatus;

    private final LocalDateTime createdDateTime;

    private final LocalDateTime etlDateTime;
}

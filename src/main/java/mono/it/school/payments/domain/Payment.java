package mono.it.school.payments.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;
import mono.it.school.payments.constants.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Payment {

    private final UUID paymentID;

    private final UUID templateID;

    private final long cardNumber;

    private final float paymentAmount;

    private final PaymentStatus paymentStatus;

    private final LocalDateTime createdDateTime;

    private final LocalDateTime etlDateTime;
}

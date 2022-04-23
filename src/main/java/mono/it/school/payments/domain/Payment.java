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

    @NonNull
    private final UUID templateID;

    private final long cardNumber;

    private final float paymentAmount;

    private final PaymentStatus paymentStatus;

    @NonNull
    private final LocalDateTime createdDateTime;

    //TODO: Annotate this
    @NonNull
    private LocalDateTime etlDateTime;

//    public Payment(
//            UUID paymentID,
//            UUID templateID,
//            long cardNumber,
//            float paymentAmount,
//            PaymentStatus paymentStatus,
//            LocalDateTime createdDateTime,
//            LocalDateTime etlDateTime
//    ) {
//        this.paymentID = paymentID;
//        this.templateID = templateID;
//        this.cardNumber = cardNumber;
//        this.paymentAmount = paymentAmount;
//        this.paymentStatus = paymentStatus;
//        this.createdDateTime = createdDateTime.withNano(0);
//        this.etlDateTime = etlDateTime.withNano(0);
//    }
//
//    public Payment(UUID templateID, long cardNumber, float paymentAmount) {
//        this(
//                UUID.randomUUID(),
//                templateID,
//                cardNumber,
//                paymentAmount,
//                PaymentStatus.NEW,
//                LocalDateTime.now(),
//                LocalDateTime.now()
//        );
//    }
}

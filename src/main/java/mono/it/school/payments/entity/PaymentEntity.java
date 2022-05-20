package mono.it.school.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mono.it.school.payments.constants.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", schema = "mono", indexes = {
        @Index(name = "idx_paymententity", columnList = "payment_status")
})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class PaymentEntity {

    @Id
    @Column(name = "id")
    private UUID paymentID;

    @Column(name = "description")
    private String description;

    @Column(name = "template_id")
    private UUID templateID;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "payment_amount")
    private float paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;

    @Column(name = "etl_date_time")
    private LocalDateTime etlDateTime;
}

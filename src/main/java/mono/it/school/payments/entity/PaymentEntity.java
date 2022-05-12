package mono.it.school.payments.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import mono.it.school.payments.constants.PaymentStatus;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments", schema = "mono", indexes = {
        @Index(name = "idx_paymententity", columnList = "payment_status")
})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentEntity {

    @Id
    @Column(name = "id")
    @Getter
    private UUID paymentID;

    @Column(name = "template_id")
    @Getter
    @NonNull
    private UUID templateID;

    @Column(name = "card_number")
    @Getter
    private long cardNumber;

    @Column(name = "payment_amount")
    @Getter
    private float paymentAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
//    @Type(type = "pgsql_enum")
    @Getter
    @NonNull
    private PaymentStatus paymentStatus;

    @Column(name = "created_date_time")
    @Getter
    @NonNull
    private LocalDateTime createdDateTime;

    @Column(name = "etl_date_time")
    @Getter
    @NonNull
    private LocalDateTime etlDateTime;
}

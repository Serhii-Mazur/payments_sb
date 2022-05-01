package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "templates", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TemplateEntity {

    @Id
    @Column(name = "id")
    @Getter
    UUID templateID;

    @Column(name = "address_id")
    @Getter
    @NonNull
    UUID addressID;

    @Column(name = "payment_purpose")
    @Getter
    @NonNull
    String paymentPurpose;

    @Column(name = "template_name")
    @Getter
    @NonNull
    String templateName;

    @Column(name = "iban")
    @Getter
    @NonNull
    String iban;
}

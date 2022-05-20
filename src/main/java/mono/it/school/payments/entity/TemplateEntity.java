package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "templates", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class TemplateEntity {

    @Id
    @Column(name = "id")
    UUID templateID;

    @Column(name = "address_id")
    UUID addressID;

    @Column(name = "payment_purpose")
    String paymentPurpose;

    @Column(name = "template_name")
    String templateName;

    @Column(name = "iban")
    String iban;
}

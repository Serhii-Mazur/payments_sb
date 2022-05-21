package mono.it.school.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "addresses", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class AddressEntity {

    @Id
    @Column(name = "id")
    private UUID addressID;

    @Column(name = "address")
    private String address;

    @Column(name = "user_email")
    private String userEmail;
}

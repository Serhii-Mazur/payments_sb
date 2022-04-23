package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private UUID addressID;

    @Column(name = "address")
    @Getter
    @NonNull
    private String address;

    @Column(name = "user_email")
    @Getter
    @NonNull
    private String userEmail;
}

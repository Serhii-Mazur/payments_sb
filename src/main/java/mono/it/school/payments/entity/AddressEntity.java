package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AddressEntity {

    @Id
    @Column(name = "id")
    @Getter
    @NonNull
    private UUID addressID;

    @Column(name = "address")
    @Getter
    @NonNull
    private String address;

    @Column(name = "user_email")
    @Getter
    @NonNull
    private String userEmail;

    public AddressEntity(@NonNull String address, @NonNull String userEmail) {
        this.address = address;
        this.userEmail = userEmail;
    }
}

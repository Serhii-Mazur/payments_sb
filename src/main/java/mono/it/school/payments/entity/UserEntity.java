package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserEntity {

    @Column(name = "full_name")
    @Getter
    @NonNull
    private String fullName;

    @Id
    @Column(name = "email")
    @Getter
    @NonNull
    private String email;

    @Column(name = "phone_number")
    @Getter
    @NonNull
    private String phoneNumber;
}
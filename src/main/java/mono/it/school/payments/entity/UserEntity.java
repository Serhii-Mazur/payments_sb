package mono.it.school.payments.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserEntity {

    @Id
    @Column(name = "full_name")
    @Getter
    @NonNull
    private String fullName;

    @Column(name = "email")
    @Getter
    @NonNull
    private String eMail;

    @Column(name = "phone_number")
    @Getter
    @NonNull
    private String phoneNumber;
}
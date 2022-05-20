package mono.it.school.payments.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "mono")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class UserEntity {

    @Column(name = "full_name")
    private String fullName;

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
package mono.it.school.payments.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class Address {

    private UUID addressID;

    @NotBlank
    @Size(min = 6, message = "Address must have more then 5 symbols")
    private String address;

    @NotBlank
    @Email
    private String userEmail;

    public Address(String address, String userEmail) {
        this.address = address;
        this.userEmail = userEmail;
    }
}
package mono.it.school.payments.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotBlank
    @Size(min = 4, message = "FullName must have more then 3 symbols")
    private final String fullName;

    @NotBlank
    @Email(message = "Email should be valid")
    private final String email;

    @NotBlank
    @Size(min = 13, max = 13, message = "PhoneNumber must match the format '+380XXXXXXXXX'")
    private final String phoneNumber;
}

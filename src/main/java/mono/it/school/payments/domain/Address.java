package mono.it.school.payments.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class Address {

    private final UUID addressID;

    @NotBlank
    @Size(min = 6, message = "Address must have more then 5 symbols")
    private final String address;

    @NotBlank
    @Email
    private final String userEmail;
}
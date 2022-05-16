package mono.it.school.payments.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class Address {

    private final UUID addressID;

    @NotEmpty(message = "Address may not be empty")
    private final String address;

    @NotEmpty(message = "UserEmail may not be empty")
    private final String userEmail;
}
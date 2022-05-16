package mono.it.school.payments.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    @NotEmpty(message = "FullName may not be empty")
    private final String fullName;

    @NotEmpty(message = "Email may not be empty")
    private final String email;

    @NotEmpty(message = "PhoneNumber may not be empty")
    private final String phoneNumber;
}

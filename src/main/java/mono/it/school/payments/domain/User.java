package mono.it.school.payments.domain;

import lombok.*;

@Data
public class User {

    @NonNull
    private final String fullName;

    @NonNull
    private final String email;

    @NonNull
    private final String phoneNumber;
}

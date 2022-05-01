package mono.it.school.payments.domain;

import lombok.*;

import java.util.UUID;

@Data
public class Address {

    private final UUID addressID;

    @NonNull
    private final String address;

    @NonNull
    private final String userEmail;
}
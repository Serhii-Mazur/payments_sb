package mono.it.school.payments.domain;

import lombok.*;

@Data
public class User extends AbstractDomainEntity {

    @NonNull
    private final String fullName;

    @NonNull
    private final String eMail;

    @NonNull
    private final String phoneNumber;
}

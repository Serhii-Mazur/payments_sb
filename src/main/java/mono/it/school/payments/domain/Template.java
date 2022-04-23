package mono.it.school.payments.domain;

import lombok.*;

import java.util.UUID;

@Data
public class Template {

    private final UUID templateID;

    @NonNull
    private final UUID addressID;

    @NonNull
    private final String paymentPurpose;

    @NonNull
    private final String templateName;

    @NonNull
    private final String iban;
}

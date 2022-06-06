package mono.it.school.payments.api.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class RequestAddressDto {

    @NotBlank
    @Size(min = 6, message = "Address must have more then 5 symbols")
    private String address;

    @NotBlank
    @Email
    private String userEmail;
}
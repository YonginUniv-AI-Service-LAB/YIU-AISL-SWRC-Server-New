package SWRC.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationRequest {

    @NotBlank
    @Email
    private String email;

    public SendVerificationRequest(String email) {
        this.email = email;
    }
}

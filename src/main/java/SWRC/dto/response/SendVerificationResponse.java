package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendVerificationResponse {
    private String message;
    private String email;

    public SendVerificationResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }
}

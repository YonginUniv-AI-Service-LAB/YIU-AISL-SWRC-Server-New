package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequestResponse {
    private String message;
    private String email;

    public PasswordResetRequestResponse(String message, String email) {
        this.message = message;
        this.email = email;
    }
}

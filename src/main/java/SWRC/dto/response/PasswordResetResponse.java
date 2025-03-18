package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetResponse {
    private String message;
    private boolean success;

    public PasswordResetResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}

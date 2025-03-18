package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailResponse {
    private String message;
    private boolean verified;

    public VerifyEmailResponse(String message, boolean verified) {
        this.message = message;
        this.verified = verified;
    }
}

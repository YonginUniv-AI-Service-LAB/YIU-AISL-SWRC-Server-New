package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
    private String message;
    private String userId;

    public SignupResponse(String message, String userId) {
        this.message = message;
        this.userId = userId;
    }
}

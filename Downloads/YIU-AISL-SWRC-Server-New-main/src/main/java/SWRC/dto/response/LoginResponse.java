package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private String token;
    private String userId;

    public LoginResponse(String message, String token, String userId) {
        this.message = message;
        this.token = token;
        this.userId = userId;
    }
}

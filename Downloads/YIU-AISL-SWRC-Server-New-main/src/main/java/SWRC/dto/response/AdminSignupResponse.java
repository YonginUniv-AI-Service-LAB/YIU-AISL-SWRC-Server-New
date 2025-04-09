package SWRC.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignupResponse {
    private String message;
    private String adminId;

    public AdminSignupResponse(String message, String adminId) {
        this.message = message;
        this.adminId = adminId;
    }
}

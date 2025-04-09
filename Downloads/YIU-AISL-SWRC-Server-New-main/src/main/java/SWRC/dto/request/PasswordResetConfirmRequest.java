package SWRC.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetConfirmRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String token; // JWT 또는 비밀번호 변경 토큰

    @NotBlank
    @Size(min = 6, max = 20)
    private String newPassword;

    public PasswordResetConfirmRequest(String email, String token, String newPassword) {
        this.email = email;
        this.token = token;
        this.newPassword = newPassword;
    }
}

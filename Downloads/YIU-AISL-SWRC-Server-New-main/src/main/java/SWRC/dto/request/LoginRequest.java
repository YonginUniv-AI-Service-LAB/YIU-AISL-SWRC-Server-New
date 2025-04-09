package SWRC.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "로그인 요청 DTO") // 📌 Swagger에서 이 클래스 설명 추가
public class LoginRequest {

    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;

    @Schema(description = "비밀번호", example = "password123")
    private String password;
}

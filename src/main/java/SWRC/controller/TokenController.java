package SWRC.controller;

import SWRC.entity.RefreshToken;
import SWRC.exception.ApiException;
import SWRC.exception.ErrorType;
import SWRC.security.JwtUtil;
import SWRC.service.RefreshTokenService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public TokenController(JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshToken stored = refreshTokenService.getRefreshToken(request.getRefreshToken());

        if (stored == null) {
            throw new ApiException(ErrorType.INVALID_REFRESH_TOKEN);
        }

        // 🔥 수정 포인트: userId와 email을 모두 넘겨야 함
        Long userId = stored.getUserId();   // RefreshToken 엔티티에 userId 있어야 함
        String email = stored.getEmail();

        String newAccessToken = jwtUtil.generateToken(userId, email);

        return ResponseEntity.ok(new TokenResponse(newAccessToken));
    }

    @Data
    public static class RefreshRequest {
        private String refreshToken;
    }

    @Data
    public static class TokenResponse {
        private final String accessToken;
    }
}

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

        String newAccessToken = jwtUtil.generateToken(stored.getEmail());
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

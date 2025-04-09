package SWRC.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    // ✅ 시크릿 키 (32바이트 이상)
    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key";

    // ✅ 토큰 유효기간 설정
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15분
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7일

    // ✅ Access Token 생성 (userId, email 담아서 생성)
    public String generateToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email) // 기본 subject는 email로
                .claim("userId", userId) // 추가 정보로 userId 저장
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Refresh Token 생성 (email만 담아도 됨)
    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Access Token 검증 (문제 없으면 email 반환)
    public String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // 토큰이 유효하지 않음
        }
    }

    // ✅ email(Username) 추출
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // subject에 저장된 email 반환
    }

    // ✅ userId 추출 (우리가 추가한 claim)
    public Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class); // claim에서 userId 꺼내기
    }

    // ✅ RefreshToken 만료 시간 반환 (필요시 사용)
    public long getRefreshTokenExpiration() {
        return REFRESH_TOKEN_EXPIRATION;
    }
}

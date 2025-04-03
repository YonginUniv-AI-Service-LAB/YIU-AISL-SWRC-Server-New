package SWRC.service;

import SWRC.entity.RefreshToken;
import SWRC.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public void saveRefreshToken(String email, String token, long expireMillis) {
        refreshTokenRepository.deleteByEmail(email); // 기존 토큰 삭제
        refreshTokenRepository.flush(); // 💡 추가

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setEmail(email);
        refreshToken.setToken(token);
        refreshToken.setExpiredAt(LocalDateTime.now().plusSeconds(expireMillis / 1000));

        refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .filter(rt -> rt.getExpiredAt().isAfter(LocalDateTime.now()))
                .orElse(null);
    }
}
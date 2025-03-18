package SWRC.service;

import SWRC.entity.User;
import SWRC.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    // ✅ UserRepository 주입받도록 생성자 추가
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 로그인 검증을 위해 유저 정보를 반환하는 메서드
    public User loadUserByUsername(String email) {
        // 이메일로 유저를 조회하고 없으면 예외를 던집니다.
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));
    }
}

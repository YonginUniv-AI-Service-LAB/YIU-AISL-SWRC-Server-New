package SWRC.service;

import SWRC.entity.User;
import SWRC.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ 학생 회원가입
    public User registerUser(String email, String rawPassword, String name, String phoneNumber) {
        if (email == null || rawPassword == null || name == null || phoneNumber == null) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(User.Role.STUDENT)
                .profileSet(false)
                .build();

        return userRepository.save(user);
    }

    // ✅ 관리자 회원가입 (종목 포함)
    public User registerAdmin(String email, String rawPassword, String name, String phoneNumber, String sportType) {
        if (email == null || rawPassword == null || name == null || phoneNumber == null || sportType == null) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .phoneNumber(phoneNumber)
                .role(User.Role.ADMIN)
                .sportType(sportType) // ⚠️ User 엔티티에 있어야 함
                .build();

        return userRepository.save(user);
    }

    // ✅ 로그인 (비밀번호 비교)
    public boolean authenticate(String email, String rawPassword) {
        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자를 찾을 수 없습니다."));
    }



    // ✅ 비밀번호 변경 (암호화 후 저장)
    public void updatePassword(String email, String newPassword) {
        userRepository.findByEmail(email).ifPresent(user -> {

            // 🔍 테스트용
            String encodedPassword = passwordEncoder.encode(newPassword);

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // 🔍 테스트용 로그
            System.out.println("✅ 비밀번호가 변경되었습니다.");
            System.out.println("변경된 해시된 비밀번호: " + encodedPassword);
        });
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

package SWRC.service;

import SWRC.entity.User;
import SWRC.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ 회원가입 (MySQL에 저장)
    public User registerUser(String email, String password) {
        User user = new User(email, password);
        return userRepository.save(user); // 👉 MySQL에 저장됨
    }

    // ✅ 로그인 시 비밀번호 검증 (DB에서 조회)
    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    // ✅ 비밀번호 변경 (DB에 저장된 값 업데이트)
    public void updatePassword(String email, String newPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> {
            u.setPassword(newPassword);
            userRepository.save(u); // 👉 MySQL에 업데이트됨
        });
    }
}

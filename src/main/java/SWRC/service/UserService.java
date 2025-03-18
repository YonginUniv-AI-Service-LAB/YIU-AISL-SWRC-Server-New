package SWRC.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, String> userDatabase = new HashMap<>(); // (email, password) 저장

    // ✅ 회원가입 (이메일 & 비밀번호 저장)
    public void registerUser(String email, String password) {
        userDatabase.put(email, password);
    }

    // ✅ 로그인 시 비밀번호 검증
    public boolean authenticate(String email, String password) {
        return userDatabase.containsKey(email) && userDatabase.get(email).equals(password);
    }

    // ✅ 비밀번호 변경
    public void updatePassword(String email, String newPassword) {
        if (userDatabase.containsKey(email)) {
            userDatabase.put(email, newPassword);
        }
    }
}

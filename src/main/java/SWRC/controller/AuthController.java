package SWRC.controller;
//이메일을 전송하고, 인증을 처리하는 API를 제공합니다.
import SWRC.security.JwtUtil;
import SWRC.service.EmailService;
import SWRC.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final EmailService emailService;

    public AuthController(JwtUtil jwtUtil, UserService userService, EmailService emailService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.emailService = emailService;
    }

    // ✅ 1. 회원가입 요청 (이메일 인증 코드 발송)
    @PostMapping("/send-verification")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    }

    // ✅ 2. 이메일 인증 코드 검증 및 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestParam String email, @RequestParam String password, @RequestParam String code) {
        boolean isVerified = emailService.verifyCode(email, code);
        if (!isVerified) {
            return ResponseEntity.badRequest().body("잘못된 인증 코드입니다.");
        }
        userService.registerUser(email, password);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 3. 로그인 (JWT 발급)
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        if (!userService.authenticate(email, password)) {
            return ResponseEntity.status(401).body(Map.of("error", "이메일 또는 비밀번호가 잘못되었습니다."));
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }

    // ✅ 4. 비밀번호 변경 요청 (이메일 인증 코드 발송)
    @PostMapping("/password-reset-request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("비밀번호 재설정을 위한 인증 코드가 이메일로 전송되었습니다.");
    }

    // ✅ 5. 비밀번호 변경 (인증 코드 검증 후 새 비밀번호 설정)
    @PostMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String newPassword) {
        boolean isVerified = emailService.verifyCode(email, code);
        if (!isVerified) {
            return ResponseEntity.badRequest().body("잘못된 인증 코드입니다.");
        }
        userService.updatePassword(email, newPassword);
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}

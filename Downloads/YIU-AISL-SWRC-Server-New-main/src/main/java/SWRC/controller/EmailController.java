package SWRC.controller;

import SWRC.service.EmailService;
import SWRC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    // 이메일로 인증번호 보내기
    @PostMapping("/send-verification")
    public ResponseEntity<String> sendVerificationEmail(@RequestParam String email) {
        try {
            emailService.sendVerificationEmail(email);  // 이메일로 인증번호 발송
            return ResponseEntity.ok("이메일 인증번호가 발송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("이메일 발송에 실패했습니다: " + e.getMessage());
        }
    }

    // 이메일 인증번호 검증
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String email, @RequestParam String verificationCode) {
        if (emailService.verifyCode(email, verificationCode)) {
            return ResponseEntity.ok("인증번호 확인 완료!");
        } else {
            return ResponseEntity.badRequest().body("인증번호가 올바르지 않습니다.");
        }
    }
}

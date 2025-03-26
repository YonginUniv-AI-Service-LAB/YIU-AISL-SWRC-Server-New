package SWRC.controller;

import SWRC.dto.request.PasswordChangeRequest;
import SWRC.exception.ApiException;
import SWRC.exception.ErrorType;
import SWRC.service.EmailService;
import SWRC.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {

    private final UserService userService;
    private final EmailService emailService;

    // ✅ 비밀번호 변경 요청 처리 (이메일 + 인증번호 + 새 비밀번호)
    @PutMapping("/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordChangeRequest request) {
        boolean isVerified = emailService.verifyCode(request.getEmail(), request.getVerificationCode());
        if (!isVerified) {
            throw new ApiException(ErrorType.AUTH_CODE_MISMATCH);
        }

        userService.updatePassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }
}

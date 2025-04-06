package SWRC.controller;

import SWRC.dto.request.SignupRequest;
import SWRC.dto.request.AdminSignupRequest;
import SWRC.entity.User;
import SWRC.exception.ApiException;
import SWRC.exception.ErrorType;
import SWRC.security.JwtUtil;
import SWRC.service.EmailService;
import SWRC.service.RefreshTokenService;
import SWRC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final EmailService emailService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(JwtUtil jwtUtil, UserService userService, EmailService emailService, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.emailService = emailService;
        this.refreshTokenService = refreshTokenService;
    }

    // ✅ 1. 이메일 인증 코드 발송
    @GetMapping("/send-verification")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        // 이미 가입된 이메일인지 확인
        if (userService.existsByEmail(email)) {
            return ResponseEntity
                    .status(409) // Conflict
                    .body("이미 가입된 이메일입니다.");
        }

        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    }

    // ✅ 2. 학생 회원가입 (이메일 인증 + 유효성 검사)
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
        boolean isVerified = emailService.verifyCode(request.getEmail(), request.getVerificationCode());
        if (!isVerified) {
            throw new ApiException(ErrorType.AUTH_CODE_MISMATCH);
        }

        User registeredUser = userService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getPhoneNumber()
        );

        if (registeredUser == null) {
            throw new ApiException(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return ResponseEntity.ok("회원가입 성공!");
    }

    // ✅ 3. 관리자 회원가입 (이메일 인증 + 종목 선택 포함)
    @PostMapping("/signup/admin")
    public ResponseEntity<String> signupAdmin(@Valid @RequestBody AdminSignupRequest request) {
        boolean isVerified = emailService.verifyCode(request.getEmail(), request.getVerificationCode());
        if (!isVerified) {
            throw new ApiException(ErrorType.AUTH_CODE_MISMATCH);
        }

        User registeredUser = userService.registerAdmin(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getPhoneNumber(),
                request.getSportType()
        );

        if (registeredUser == null) {
            throw new ApiException(ErrorType.MISSING_REQUIRED_FIELDS);
        }

        return ResponseEntity.ok("관리자 회원가입 성공!");
    }

    // ✅ 4. 로그인 (JWT + Refresh Token 발급 + isProfileSet 포함)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
        if (!userService.authenticate(email, password)) {
            throw new ApiException(ErrorType.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.generateToken(email);
        String refreshToken = jwtUtil.generateRefreshToken(email);

        refreshTokenService.saveRefreshToken(email, refreshToken, 7 * 24 * 60 * 60 * 1000); // 7일

        // 🔥 사용자 정보 조회 → isProfileSet 확인
        User user = userService.findByEmail(email);

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "isProfileSet", user.isProfileSet(),
                "userId", user.getId()
        ));
    }

    // ✅ 5. 비밀번호 변경 요청 (이메일 인증 코드 발송)
    @PostMapping("/password-reset-request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("비밀번호 재설정을 위한 인증 코드가 이메일로 전송되었습니다.");
    }

    // ✅ 6. 비밀번호 변경 (이메일 인증 + 새 비밀번호 설정)
    @PutMapping("/password-reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String newPassword) {
        boolean isVerified = emailService.verifyCode(email, code);
        if (!isVerified) {
            throw new ApiException(ErrorType.AUTH_CODE_MISMATCH);
        }

        userService.updatePassword(email, newPassword);
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
    }

    // ✅ 7. 현재 로그인한 사용자 정보 조회 (JWT에서 이메일 추출)
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        // "Bearer eyJhbGci..." 형태에서 토큰만 추출
        String token = authHeader.replace("Bearer ", "");

        // 토큰에서 이메일 추출
        String email = jwtUtil.extractUsername(token);

        // 이메일로 사용자 조회
        User user = userService.findByEmail(email);

        return ResponseEntity.ok(Map.of(
                "email", user.getEmail(),
                "role", user.getRole().name(),
                "isProfileSet", user.getProfileSet(),
                "userId", user.getId()
        ));
    }
}

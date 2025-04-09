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

    // ✅ 이메일 인증 코드 발송
    @GetMapping("/send-verification")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        if (userService.existsByEmail(email)) {
            return ResponseEntity
                    .status(409)
                    .body("이미 가입된 이메일입니다.");
        }

        emailService.sendVerificationEmail(email);
        return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    }

    // ✅ 학생 회원가입
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

    // ✅ 관리자 회원가입
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

    // ✅ 로그인 (Access Token + Refresh Token 발급)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
        if (!userService.authenticate(email, password)) {
            throw new ApiException(ErrorType.INVALID_PASSWORD);
        }


        User user = userService.findByEmail(email);

// ✅ 관리자일 때만 승인 상태 확인
        if (user.getRole() == User.Role.ADMIN && user.getApprovalStatus() != User.ApprovalStatus.APPROVED) {
            throw new ApiException(ErrorType.UNAUTHORIZED); // 관리자 미승인
        }

        String accessToken = jwtUtil.generateToken(user.getId(), user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(email);

            refreshTokenService.saveRefreshToken(user.getId(), email, refreshToken, 7 * 24 * 60 * 60 * 1000);


            return ResponseEntity.ok(Map.of(
                    "accessToken", accessToken,
                    "refreshToken", refreshToken,
                    "isProfileSet", user.isProfileSet(),
                    "userId", user.getId(),
                    "role", user.getRole().name() // 🔄 프론트에 role도 넘기면 편함!
            ));
        }

        // ✅ 비밀번호 변경 요청
        @PostMapping("/password-reset-request")
        public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
            emailService.sendVerificationEmail(email);
            return ResponseEntity.ok("비밀번호 재설정을 위한 인증 코드가 이메일로 전송되었습니다.");
        }

        // ✅ 비밀번호 변경
        @PutMapping("/password-reset")
        public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String newPassword) {
            boolean isVerified = emailService.verifyCode(email, code);
            if (!isVerified) {
                throw new ApiException(ErrorType.AUTH_CODE_MISMATCH);
            }

            userService.updatePassword(email, newPassword);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        }

        // ✅ 현재 로그인한 사용자 정보 조회
        @GetMapping("/me")
        public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
            String token = authHeader.replace("Bearer ", "");

            String email = jwtUtil.extractUsername(token);
            User user = userService.findByEmail(email);

            return ResponseEntity.ok(Map.of(
                    "email", user.getEmail(),
                    "role", user.getRole().name(),
                    "isProfileSet", user.getProfileSet(),
                    "userId", user.getId()
            ));
        }
    }
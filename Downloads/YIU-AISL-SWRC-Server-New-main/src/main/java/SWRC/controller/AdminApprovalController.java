package SWRC.controller;

import SWRC.entity.Admin;
import SWRC.exception.ApiException;
import SWRC.exception.ErrorType;
import SWRC.security.UserDetailsImpl;
import SWRC.service.AdminApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApprovalController {

    private final AdminApprovalService adminApprovalService;
    private static final String SUPER_ADMIN_EMAIL = "alsehd2414@gmail.com";  // 최고관리자

    // ✅ 승인 대기 관리자 목록 조회
    @GetMapping("/pending")
    public ResponseEntity<List<Admin>> getPendingAdmins(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        validateSuperAdmin(userDetails);
        return ResponseEntity.ok(adminApprovalService.getPendingAdmins());
    }

    // ✅ 승인된 관리자 목록 조회
    @GetMapping("/approved")
    public ResponseEntity<List<Admin>> getApprovedAdmins(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        validateSuperAdmin(userDetails);
        return ResponseEntity.ok(adminApprovalService.getApprovedAdmins());
    }

    // ✅ 관리자 승인
    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approveAdmin(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        validateSuperAdmin(userDetails);
        adminApprovalService.approveAdmin(id);
        return ResponseEntity.ok("관리자 승인 완료");
    }

    // ✅ 관리자 거절
    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectAdmin(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        validateSuperAdmin(userDetails);
        adminApprovalService.rejectAdmin(id);
        return ResponseEntity.ok("관리자 거절 처리 완료");
    }

    // ✅ 관리자 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        validateSuperAdmin(userDetails);
        adminApprovalService.deleteAdmin(id);
        return ResponseEntity.ok("관리자 삭제 완료");
    }

    // ✅ 수정된 validateSuperAdmin
    private void validateSuperAdmin(UserDetailsImpl userDetails) {
        if (userDetails == null || !SUPER_ADMIN_EMAIL.equals(userDetails.getUsername())) {
            throw new ApiException(ErrorType.UNAUTHORIZED);
        }
    }
}

package SWRC.service;  // 최고관리자

import SWRC.entity.Admin;
import SWRC.entity.User.ApprovalStatus;
import SWRC.exception.ApiException;
import SWRC.exception.ErrorType;
import SWRC.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminApprovalService {

    private final AdminRepository adminRepository;

    // ✅ 승인 대기 중인 관리자 목록
    public List<Admin> getPendingAdmins() {
        return adminRepository.findByApprovalStatus(ApprovalStatus.PENDING);
    }

    // ✅ 승인 완료된 관리자 목록
    public List<Admin> getApprovedAdmins() {
        return adminRepository.findByApprovalStatus(ApprovalStatus.APPROVED);
    }

    // ✅ 관리자 승인 처리
    public void approveAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
        admin.setApprovalStatus(ApprovalStatus.APPROVED);
        adminRepository.save(admin);
    }

    // ✅ 관리자 거절 처리
    public void rejectAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
        admin.setApprovalStatus(ApprovalStatus.REJECTED);
        adminRepository.save(admin);
    }

    // ✅ 관리자 삭제 처리
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
        adminRepository.delete(admin);
    }
}

package SWRC.repository;  // 관리자 repository

import SWRC.entity.Admin;
import SWRC.entity.User.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
    List<Admin> findByApprovalStatus(ApprovalStatus status);
}

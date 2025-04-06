package SWRC.repository;

import SWRC.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // ✅ 유저 ID를 기준으로 프로필을 찾는 메서드 추가
    Optional<Profile> findByUser_Id(Long userId);

}

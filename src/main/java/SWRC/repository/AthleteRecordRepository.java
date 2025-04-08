package SWRC.repository;

import SWRC.entity.AthleteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AthleteRecordRepository extends JpaRepository<AthleteRecord, Long> {

    // ✅ userId로 전체 기록 조회
    List<AthleteRecord> findAllByUserId(Long userId);

    // ✅ id + userId로 특정 기록 조회
    Optional<AthleteRecord> findByIdAndUserId(Long id, Long userId);
}

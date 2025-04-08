package SWRC.repository;

import SWRC.entity.MatchRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MatchRecordRepository extends JpaRepository<MatchRecord, Long> {

    // 추가할 메소드들
    List<MatchRecord> findAllByUserId(Long userId);   // userId에 해당하는 모든 경기 기록 가져오기

    Optional<MatchRecord> findByIdAndUserId(Long id, Long userId);  // 특정 userId + 기록 id에 해당하는 기록 가져오기
}

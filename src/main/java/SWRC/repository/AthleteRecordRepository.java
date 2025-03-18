package SWRC.repository;

import SWRC.entity.AthleteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AthleteRecordRepository extends JpaRepository<AthleteRecord, Long> {
}

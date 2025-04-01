// AthleteRecordService.java
package SWRC.service;

import SWRC.dto.request.AthleteRecordRequest;
import SWRC.entity.AthleteRecord;
import SWRC.repository.AthleteRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteRecordService {

    private final AthleteRecordRepository athleteRecordRepository;

    public AthleteRecordService(AthleteRecordRepository athleteRecordRepository) {
        this.athleteRecordRepository = athleteRecordRepository;
    }

    public AthleteRecord createAthleteRecord(AthleteRecordRequest request) {
        AthleteRecord record = new AthleteRecord();
        record.setRecordDate(request.getRecordDate());
        record.setRecordValue(request.getRecordValue());
        record.setEtc(request.getEtc());
        record.setEvent("종목 입력"); // 기본값 또는 외부에서 설정 가능
        record.setUnit("단위 입력"); // 예: 초, m 등
        return athleteRecordRepository.save(record);
    }

    public List<AthleteRecord> getAllAthleteRecords() {
        return athleteRecordRepository.findAll();
    }

    public Optional<AthleteRecord> getAthleteRecordById(Long id) {
        return athleteRecordRepository.findById(id);
    }

    public AthleteRecord updateAthleteRecord(Long id, AthleteRecordRequest request) {
        return athleteRecordRepository.findById(id)
                .map(record -> {
                    record.setRecordDate(request.getRecordDate());
                    record.setRecordValue(request.getRecordValue());
                    record.setEtc(request.getEtc());
                    return athleteRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("AthleteRecord not found with id: " + id));
    }

    public void deleteAthleteRecord(Long id) {
        athleteRecordRepository.deleteById(id);
    }
}

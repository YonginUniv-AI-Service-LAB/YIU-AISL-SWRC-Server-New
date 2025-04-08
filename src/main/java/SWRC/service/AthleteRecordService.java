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

    // ✅ Create
    public AthleteRecord createAthleteRecord(Long userId, AthleteRecordRequest request) {
        AthleteRecord record = new AthleteRecord();
        record.setUserId(userId); // userId 저장!
        record.setRecordDate(request.getRecordDate());
        record.setRecordValue(request.getRecordValue());
        record.setEtc(request.getEtc());
        record.setEvent("종목 입력"); // 기본값 또는 외부에서 설정 가능
        record.setUnit("단위 입력");  // 예: 초, m 등
        return athleteRecordRepository.save(record);
    }

    // ✅ Read All
    public List<AthleteRecord> getAllAthleteRecords(Long userId) {
        return athleteRecordRepository.findAllByUserId(userId);
    }

    // ✅ Read By ID
    public Optional<AthleteRecord> getAthleteRecordById(Long userId, Long recordId) {
        return athleteRecordRepository.findByIdAndUserId(recordId, userId);
    }

    // ✅ Update
    public AthleteRecord updateAthleteRecord(Long userId, Long recordId, AthleteRecordRequest request) {
        return athleteRecordRepository.findByIdAndUserId(recordId, userId)
                .map(record -> {
                    record.setRecordDate(request.getRecordDate());
                    record.setRecordValue(request.getRecordValue());
                    record.setEtc(request.getEtc());
                    return athleteRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("해당 선수 기록이 존재하지 않거나 권한이 없습니다."));
    }

    // ✅ Delete
    public void deleteAthleteRecord(Long userId, Long recordId) {
        AthleteRecord record = athleteRecordRepository.findByIdAndUserId(recordId, userId)
                .orElseThrow(() -> new RuntimeException("해당 선수 기록이 존재하지 않거나 권한이 없습니다."));
        athleteRecordRepository.delete(record);
    }
}

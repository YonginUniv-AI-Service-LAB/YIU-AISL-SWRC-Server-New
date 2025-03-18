package SWRC.service;

import SWRC.entity.AthleteRecord;
import SWRC.repository.AthleteRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteRecordService {

    private final AthleteRecordRepository athleteRecordRepository;

    public AthleteRecordService(AthleteRecordRepository athleteRecordRepository) {
        this.athleteRecordRepository = athleteRecordRepository;
    }

    public AthleteRecord createAthleteRecord(AthleteRecord record) {
        return athleteRecordRepository.save(record);
    }

    public List<AthleteRecord> getAllAthleteRecords() {
        return athleteRecordRepository.findAll();
    }

    public void deleteAthleteRecord(Long id) {
        athleteRecordRepository.deleteById(id);
    }
}

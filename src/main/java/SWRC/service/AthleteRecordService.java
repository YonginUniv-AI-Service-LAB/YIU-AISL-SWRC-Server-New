package SWRC.service;

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

    // Create
    public AthleteRecord createAthleteRecord(AthleteRecord record) {
        return athleteRecordRepository.save(record);
    }

    // Read All
    public List<AthleteRecord> getAllAthleteRecords() {
        return athleteRecordRepository.findAll();
    }

    // Read By ID
    public Optional<AthleteRecord> getAthleteRecordById(Long id) {
        return athleteRecordRepository.findById(id);
    }

    // Update
    public AthleteRecord updateAthleteRecord(Long id, AthleteRecord updatedRecord) {
        return athleteRecordRepository.findById(id)
                .map(record -> {
                    record.setEvent(updatedRecord.getEvent());
                    record.setUnit(updatedRecord.getUnit());
                    record.setRecordDate(updatedRecord.getRecordDate());
                    record.setRecordValue(updatedRecord.getRecordValue());
                    record.setEtc(updatedRecord.getEtc());
                    return athleteRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("AthleteRecord not found with id: " + id));
    }

    // Delete
    public void deleteAthleteRecord(Long id) {
        athleteRecordRepository.deleteById(id);
    }
}

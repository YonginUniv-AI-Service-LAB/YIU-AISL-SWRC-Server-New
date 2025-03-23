package SWRC.service;

import SWRC.entity.MatchRecord;
import SWRC.repository.MatchRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchRecordService {

    private final MatchRecordRepository matchRecordRepository;

    public MatchRecordService(MatchRecordRepository matchRecordRepository) {
        this.matchRecordRepository = matchRecordRepository;
    }

    public MatchRecord createMatchRecord(MatchRecord record) {
        return matchRecordRepository.save(record);
    }

    public List<MatchRecord> getAllMatchRecords() {
        return matchRecordRepository.findAll();
    }

    public Optional<MatchRecord> getMatchRecordById(Long id) {
        return matchRecordRepository.findById(id);
    }

    public MatchRecord updateMatchRecord(Long id, MatchRecord updatedRecord) {
        return matchRecordRepository.findById(id)
                .map(record -> {
                    record.setMatchDate(updatedRecord.getMatchDate());
                    record.setCompetitionName(updatedRecord.getCompetitionName());
                    record.setOpponent(updatedRecord.getOpponent());
                    record.setResult(updatedRecord.getResult());
                    record.setEtc(updatedRecord.getEtc());
                    return matchRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("MatchRecord not found with id: " + id));
    }

    public void deleteMatchRecord(Long id) {
        matchRecordRepository.deleteById(id);
    }
}

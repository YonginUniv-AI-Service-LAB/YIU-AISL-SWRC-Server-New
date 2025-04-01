// MatchRecordService.java
package SWRC.service;

import SWRC.dto.request.MatchRecordRequest;
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

    public MatchRecord createMatchRecord(MatchRecordRequest request) {
        MatchRecord record = new MatchRecord();
        record.setTournamentName(request.getTournamentName());
        record.setMatchDate(request.getMatchDate());
        record.setOpponent(request.getOpponent());
        record.setResult(request.getResult());
        record.setEtc(request.getEtc());
        return matchRecordRepository.save(record);
    }

    public List<MatchRecord> getAllMatchRecords() {
        return matchRecordRepository.findAll();
    }

    public Optional<MatchRecord> getMatchRecordById(Long id) {
        return matchRecordRepository.findById(id);
    }

    public MatchRecord updateMatchRecord(Long id, MatchRecordRequest request) {
        return matchRecordRepository.findById(id)
                .map(record -> {
                    record.setTournamentName(request.getTournamentName());
                    record.setMatchDate(request.getMatchDate());
                    record.setOpponent(request.getOpponent());
                    record.setResult(request.getResult());
                    record.setEtc(request.getEtc());
                    return matchRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("MatchRecord not found with id: " + id));
    }

    public void deleteMatchRecord(Long id) {
        matchRecordRepository.deleteById(id);
    }
}

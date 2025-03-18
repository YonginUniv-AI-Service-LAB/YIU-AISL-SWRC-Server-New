package SWRC.service;

import SWRC.entity.MatchRecord;
import SWRC.repository.MatchRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteMatchRecord(Long id) {
        matchRecordRepository.deleteById(id);
    }
}

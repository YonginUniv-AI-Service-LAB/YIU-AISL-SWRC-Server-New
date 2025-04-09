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

    public MatchRecord createMatchRecord(Long userId, MatchRecordRequest request) {
        MatchRecord record = new MatchRecord();
        record.setUserId(userId); // 추가! 토큰에서 읽은 userId 넣어줌
        record.setTournamentName(request.getTournamentName());
        record.setMatchDate(request.getMatchDate());
        record.setOpponent(request.getOpponent());
        record.setResult(request.getResult());
        record.setEtc(request.getEtc());
        return matchRecordRepository.save(record);
    }

    public List<MatchRecord> getAllMatchRecords(Long userId) {
        return matchRecordRepository.findAllByUserId(userId);
    }

    public Optional<MatchRecord> getMatchRecordById(Long userId, Long matchRecordId) {
        return matchRecordRepository.findByIdAndUserId(matchRecordId, userId);
    }

    public MatchRecord updateMatchRecord(Long userId, Long matchRecordId, MatchRecordRequest request) {
        return matchRecordRepository.findByIdAndUserId(matchRecordId, userId)
                .map(record -> {
                    record.setTournamentName(request.getTournamentName());
                    record.setMatchDate(request.getMatchDate());
                    record.setOpponent(request.getOpponent());
                    record.setResult(request.getResult());
                    record.setEtc(request.getEtc());
                    return matchRecordRepository.save(record);
                })
                .orElseThrow(() -> new RuntimeException("해당 경기 기록이 없거나 권한이 없습니다."));
    }

    public void deleteMatchRecord(Long userId, Long matchRecordId) {
        MatchRecord record = matchRecordRepository.findByIdAndUserId(matchRecordId, userId)
                .orElseThrow(() -> new RuntimeException("해당 경기 기록이 없거나 권한이 없습니다."));
        matchRecordRepository.delete(record);
    }
}

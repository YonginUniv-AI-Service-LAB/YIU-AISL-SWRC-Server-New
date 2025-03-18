package SWRC.controller;

import SWRC.entity.MatchRecord;
import SWRC.service.MatchRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-records")
public class MatchRecordController {

    private final MatchRecordService matchRecordService;

    public MatchRecordController(MatchRecordService matchRecordService) {
        this.matchRecordService = matchRecordService;
    }

    @PostMapping
    public ResponseEntity<MatchRecord> createMatchRecord(@RequestBody MatchRecord record) {
        return ResponseEntity.ok(matchRecordService.createMatchRecord(record));
    }

    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAllMatchRecords() {
        return ResponseEntity.ok(matchRecordService.getAllMatchRecords());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatchRecord(@PathVariable Long id) {
        matchRecordService.deleteMatchRecord(id);
        return ResponseEntity.ok("경기 기록 삭제 완료");
    }
}

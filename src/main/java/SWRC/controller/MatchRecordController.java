// MatchRecordController.java
package SWRC.controller;

import SWRC.dto.request.MatchRecordRequest;
import SWRC.entity.MatchRecord;
import SWRC.service.MatchRecordService;
import jakarta.validation.Valid;
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
    public ResponseEntity<MatchRecord> createMatchRecord(@Valid @RequestBody MatchRecordRequest request) {
        return ResponseEntity.ok(matchRecordService.createMatchRecord(request));
    }

    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAllMatchRecords() {
        return ResponseEntity.ok(matchRecordService.getAllMatchRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchRecord> getMatchRecordById(@PathVariable Long id) {
        return matchRecordService.getMatchRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchRecord> updateMatchRecord(@PathVariable Long id,
                                                         @Valid @RequestBody MatchRecordRequest request) {
        return ResponseEntity.ok(matchRecordService.updateMatchRecord(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatchRecord(@PathVariable Long id) {
        matchRecordService.deleteMatchRecord(id);
        return ResponseEntity.ok("경기 기록 삭제 완료");
    }
}

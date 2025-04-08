package SWRC.controller;

import SWRC.dto.request.MatchRecordRequest;
import SWRC.entity.MatchRecord;
import SWRC.service.MatchRecordService;
import SWRC.security.UserDetailsImpl; // 너가 사용하는 JWT UserDetails
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<MatchRecord> createMatchRecord(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @Valid @RequestBody MatchRecordRequest request) {
        Long userId = userDetails.getId(); // JWT에서 추출한 userId
        return ResponseEntity.ok(matchRecordService.createMatchRecord(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<MatchRecord>> getAllMatchRecords(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(matchRecordService.getAllMatchRecords(userId));
    }

    @GetMapping("/{matchRecordId}")
    public ResponseEntity<MatchRecord> getMatchRecordById(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                          @PathVariable Long matchRecordId) {
        Long userId = userDetails.getId();
        return matchRecordService.getMatchRecordById(userId, matchRecordId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{matchRecordId}")
    public ResponseEntity<MatchRecord> updateMatchRecord(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                         @PathVariable Long matchRecordId,
                                                         @Valid @RequestBody MatchRecordRequest request) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(matchRecordService.updateMatchRecord(userId, matchRecordId, request));
    }

    @DeleteMapping("/{matchRecordId}")
    public ResponseEntity<String> deleteMatchRecord(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @PathVariable Long matchRecordId) {
        Long userId = userDetails.getId();
        matchRecordService.deleteMatchRecord(userId, matchRecordId);
        return ResponseEntity.ok("경기 기록 삭제 완료");
    }
}

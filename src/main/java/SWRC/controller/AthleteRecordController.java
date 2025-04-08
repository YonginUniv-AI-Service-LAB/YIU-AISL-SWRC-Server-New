package SWRC.controller;

import SWRC.dto.request.AthleteRecordRequest;
import SWRC.entity.AthleteRecord;
import SWRC.service.AthleteRecordService;
import SWRC.security.UserDetailsImpl; // 추가 필요!
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athlete-records")
public class AthleteRecordController {

    private final AthleteRecordService athleteRecordService;

    public AthleteRecordController(AthleteRecordService athleteRecordService) {
        this.athleteRecordService = athleteRecordService;
    }

    // ✅ Create
    @PostMapping
    public ResponseEntity<AthleteRecord> createAthleteRecord(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody AthleteRecordRequest request) {

        Long userId = userDetails.getId();
        AthleteRecord savedRecord = athleteRecordService.createAthleteRecord(userId, request);
        return ResponseEntity.ok(savedRecord);
    }

    // ✅ Read All (내 선수 기록 전체 조회)
    @GetMapping
    public ResponseEntity<List<AthleteRecord>> getAllAthleteRecords(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getId();
        return ResponseEntity.ok(athleteRecordService.getAllAthleteRecords(userId));
    }

    // ✅ Read By ID (내 선수 기록 조회)
    @GetMapping("/{athleteRecordId}")
    public ResponseEntity<AthleteRecord> getAthleteRecordById(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long athleteRecordId) {

        Long userId = userDetails.getId();
        return athleteRecordService.getAthleteRecordById(userId, athleteRecordId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update (내 선수 기록 수정)
    @PutMapping("/{athleteRecordId}")
    public ResponseEntity<AthleteRecord> updateAthleteRecord(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long athleteRecordId,
            @Valid @RequestBody AthleteRecordRequest request) {

        Long userId = userDetails.getId();
        AthleteRecord updated = athleteRecordService.updateAthleteRecord(userId, athleteRecordId, request);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete (내 선수 기록 삭제)
    @DeleteMapping("/{athleteRecordId}")
    public ResponseEntity<String> deleteAthleteRecord(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long athleteRecordId) {

        Long userId = userDetails.getId();
        athleteRecordService.deleteAthleteRecord(userId, athleteRecordId);
        return ResponseEntity.ok("선수 기록 삭제 완료");
    }
}

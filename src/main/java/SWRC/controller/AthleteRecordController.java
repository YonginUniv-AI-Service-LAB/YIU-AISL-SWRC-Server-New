package SWRC.controller;

import SWRC.entity.AthleteRecord;
import SWRC.service.AthleteRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athlete-records")
public class AthleteRecordController {

    private final AthleteRecordService athleteRecordService;

    public AthleteRecordController(AthleteRecordService athleteRecordService) {
        this.athleteRecordService = athleteRecordService;
    }

    // Create
    @PostMapping
    public ResponseEntity<AthleteRecord> createAthleteRecord(@RequestBody AthleteRecord record) {
        return ResponseEntity.ok(athleteRecordService.createAthleteRecord(record));
    }

    // Read All
    @GetMapping
    public ResponseEntity<List<AthleteRecord>> getAllAthleteRecords() {
        return ResponseEntity.ok(athleteRecordService.getAllAthleteRecords());
    }

    // Read By ID
    @GetMapping("/{id}")
    public ResponseEntity<AthleteRecord> getAthleteRecordById(@PathVariable Long id) {
        return athleteRecordService.getAthleteRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<AthleteRecord> updateAthleteRecord(
            @PathVariable Long id,
            @RequestBody AthleteRecord updatedRecord) {
        AthleteRecord updated = athleteRecordService.updateAthleteRecord(id, updatedRecord);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAthleteRecord(@PathVariable Long id) {
        athleteRecordService.deleteAthleteRecord(id);
        return ResponseEntity.ok("선수 기록 삭제 완료");
    }
}

package SWRC.controller;

import SWRC.entity.Profile;
import SWRC.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        return ResponseEntity.ok(profileService.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//    userId로 프로필을 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Profile> updateProfileByUserId(
            @PathVariable Long userId,
            @RequestBody Profile updatedProfile
    ) {
        return ResponseEntity.ok(profileService.updateProfileByUserId(userId, updatedProfile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok("프로필 삭제 완료");
    }
}

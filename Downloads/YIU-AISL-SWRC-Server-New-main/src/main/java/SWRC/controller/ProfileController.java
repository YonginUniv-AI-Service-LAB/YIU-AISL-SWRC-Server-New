package SWRC.controller;

import SWRC.entity.Profile;
import SWRC.security.UserDetailsImpl;
import SWRC.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // ✅ 프로필 생성 (로그인 한 사용자 기준)
    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(profileService.createProfile(userId, profile));
    }

    // ✅ 전체 프로필 조회 (관리자용 등)
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // ✅ 현재 로그인한 사용자의 프로필 조회
    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        return profileService.getProfileByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 현재 로그인한 사용자의 프로필 수정
    @PutMapping("/me")
    public ResponseEntity<Profile> updateMyProfile(
            @RequestBody Profile updatedProfile,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Long userId = userDetails.getId();
        return ResponseEntity.ok(profileService.updateProfileByUserId(userId, updatedProfile));
    }

    // ✅ (필요하면) 프로필 삭제도 자신 거만 삭제하게 제한할 수 있음
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        profileService.deleteProfileByUserId(userId);
        return ResponseEntity.ok("프로필 삭제 완료");
    }
}

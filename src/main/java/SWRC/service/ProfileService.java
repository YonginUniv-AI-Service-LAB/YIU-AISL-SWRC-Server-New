package SWRC.service;

import SWRC.entity.Profile;
import SWRC.entity.User;
import SWRC.repository.ProfileRepository;
import SWRC.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    // ✅ userId를 받아서 프로필 생성
    public Profile createProfile(Long userId, Profile profile) {
        // 1. userId로 User 찾아오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 2. 프로필에 User 연결
        profile.setUser(user);

        // 3. 프로필 저장
        Profile savedProfile = profileRepository.save(profile);

        // 4. 유저의 profileSet을 true로 변경
        user.setProfileSet(true);
        userRepository.save(user);

        return savedProfile;
    }

    // ✅ 전체 프로필 조회
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    // ✅ 프로필 ID로 조회 (사용 X 가능성 있음)
    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    // ✅ userId로 프로필 조회
    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUser_Id(userId);
    }

    // ✅ userId로 프로필 수정
    public Profile updateProfileByUserId(Long userId, Profile updatedProfile) {
        return profileRepository.findByUser_Id(userId)
                .map(profile -> {
                    profile.setName(updatedProfile.getName());
                    profile.setBirthDate(updatedProfile.getBirthDate());
                    profile.setGender(updatedProfile.getGender());
                    profile.setHeight(updatedProfile.getHeight());
                    profile.setWeight(updatedProfile.getWeight());
                    profile.setEvent(updatedProfile.getEvent());
                    profile.setUnit(updatedProfile.getUnit());
                    return profileRepository.save(profile);
                })
                .orElseThrow(() -> new RuntimeException("User ID에 해당하는 프로필을 찾을 수 없습니다."));
    }

    // ✅ userId로 프로필 삭제
    public void deleteProfileByUserId(Long userId) {
        Profile profile = profileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("User ID에 해당하는 프로필을 찾을 수 없습니다."));
        profileRepository.delete(profile);

        // 유저의 profileSet도 false로 돌려줄 수 있음 (선택사항)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        user.setProfileSet(false);
        userRepository.save(user);
    }
}

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

    public Profile createProfile(Profile profile) {
        // 1. 프로필 저장
        Profile savedProfile = profileRepository.save(profile);

        // 2. 해당 유저의 profileSet = true 로 변경
        User user = savedProfile.getUser();
        if (user != null && user.getId() != null) {
            User fullUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
            fullUser.setProfileSet(true);
            userRepository.save(fullUser);
        }

        return savedProfile;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUser_Id(userId);
    }

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

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

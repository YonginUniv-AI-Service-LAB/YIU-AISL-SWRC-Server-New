package SWRC.service;

import SWRC.entity.Profile;
import SWRC.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile updateProfile(Long id, Profile updatedProfile) {
        return profileRepository.findById(id)
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
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

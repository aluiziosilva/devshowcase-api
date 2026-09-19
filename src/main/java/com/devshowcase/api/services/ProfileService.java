package com.devshowcase.api.services;

import com.devshowcase.api.dtos.CreateProfileDTO;
import com.devshowcase.api.dtos.ProfileResponseDTO;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDTO create(CreateProfileDTO dto) {
        Profile profile = new Profile();
        profile.setName(dto.name());
        profile.setBio(dto.bio());

        Profile savedProfile = profileRepository.save(profile);
        return new ProfileResponseDTO(savedProfile);
    }

    public ProfileResponseDTO findById(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + id));
        return new ProfileResponseDTO(profile);
    }
}
package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateProfileDTO;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.repositories.ProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired 
    private ProfileRepository profileRepository;

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody @Valid CreateProfileDTO dto) {
        Profile profile = new Profile(dto.name(), dto.bio());
        return ResponseEntity.status(HttpStatus.CREATED).body(profileRepository.save(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable Long id) {
        return profileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
package com.devshowcase.api.controllers;

import com.devshowcase.api.dtos.CreateProjectDTO;
import com.devshowcase.api.models.Project;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.ProjectRepository;
import com.devshowcase.api.repositories.ProfileRepository;
import com.devshowcase.api.repositories.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired 
    private ProjectRepository projectRepository;
    @Autowired 
    private ProfileRepository profileRepository;
    @Autowired 
    private TechnologyRepository techRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateProjectDTO dto) {
        Profile profile = profileRepository.findById(dto.profileId()).orElse(null);
        if (profile == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfil não encontrado");
        }

        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setUrl(dto.url());
        project.setProfile(profile);

        if (dto.technologyIds() != null && !dto.technologyIds().isEmpty()) {
            List<Technology> techs = techRepository.findAllById(dto.technologyIds());
            project.setTechnologies(new HashSet<>(techs));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(projectRepository.save(project));
    }

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        return ResponseEntity.ok(projectRepository.findAll());
    }
}
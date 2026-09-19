package com.devshowcase.api.services;

import com.devshowcase.api.dtos.CreateProjectDTO;
import com.devshowcase.api.dtos.ProjectResponseDTO;
import com.devshowcase.api.models.Profile;
import com.devshowcase.api.models.Project;
import com.devshowcase.api.models.Technology;
import com.devshowcase.api.repositories.ProfileRepository;
import com.devshowcase.api.repositories.ProjectRepository;
import com.devshowcase.api.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final TechnologyRepository technologyRepository;

    public ProjectService(ProjectRepository projectRepository,
                          ProfileRepository profileRepository,
                          TechnologyRepository technologyRepository) {
        this.projectRepository = projectRepository;
        this.profileRepository = profileRepository;
        this.technologyRepository = technologyRepository;
    }

    public ProjectResponseDTO create(CreateProjectDTO dto) {
        Profile profile = profileRepository.findById(dto.profileId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado com o ID: " + dto.profileId()));

        List<Technology> technologies = technologyRepository.findAllById(dto.technologyIds());

        Project project = new Project();
        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setUrl(dto.url());
        project.setProfile(profile);
        project.setTechnologies(new HashSet<>(technologies));

        Project savedProject = projectRepository.save(project);
        return new ProjectResponseDTO(savedProject);
    }

    public List<ProjectResponseDTO> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectResponseDTO::new)
                .toList();
    }
}
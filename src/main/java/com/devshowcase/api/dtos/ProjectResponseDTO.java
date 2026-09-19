// ProjectResponseDTO.java
package com.devshowcase.api.dtos;

import com.devshowcase.api.models.Project;
import java.util.Set;
import java.util.stream.Collectors;

public record ProjectResponseDTO(
    Long id,
    String title,
    String description,
    String url,
    ProfileResponseDTO profile,
    Set<TechnologyResponseDTO> technologies
) {
    public ProjectResponseDTO(Project entity) {
        this(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getUrl(),
            entity.getProfile() != null ? new ProfileResponseDTO(entity.getProfile()) : null,
            entity.getTechnologies().stream().map(TechnologyResponseDTO::new).collect(Collectors.toSet())
        );
    }
}
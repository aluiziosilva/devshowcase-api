package com.devshowcase.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import java.util.Set;

public record CreateProjectDTO(
    @NotBlank(message = "O título é obrigatório") String title,
    @NotBlank(message = "A descrição é obrigatória") String description,
    @NotBlank @URL(message = "URL inválida") String url,
    @NotNull(message = "ID do perfil é obrigatório") Long profileId,
    Set<Long> technologyIds
) {}
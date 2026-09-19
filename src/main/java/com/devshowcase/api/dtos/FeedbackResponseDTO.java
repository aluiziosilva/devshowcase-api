// FeedbackResponseDTO.java
package com.devshowcase.api.dtos;

import com.devshowcase.api.models.Feedback;

public record FeedbackResponseDTO(
    Long id,
    String comment
) {
    public FeedbackResponseDTO(Feedback entity) {
        this(entity.getId(), entity.getComment());
    }
}
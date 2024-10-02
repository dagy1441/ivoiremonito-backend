package com.ivoiremonito.backend.features.category.model;

import lombok.AllArgsConstructor;

public record CategoryResponse(
    Long id,
    String name,
    String description,
    String imageUrl,
    boolean isQuiz,
    boolean isCourse
) {
}

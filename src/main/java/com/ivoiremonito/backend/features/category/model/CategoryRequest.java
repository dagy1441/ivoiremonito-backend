package com.ivoiremonito.backend.features.category.model;

public record CategoryRequest(
    String name,
    String description,
    String imageUrl,
    boolean isQuiz,
    boolean isCourse
) {
}

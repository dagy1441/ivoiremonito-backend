package com.ivoiremonito.backend.features.category.model;

public record CategoryResponse(
    Long id,
    String name,
    String description,
    String imageUrl
) {
}

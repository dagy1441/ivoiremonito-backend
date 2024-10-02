package com.ivoiremonito.backend.features.category.model;

import com.ivoiremonito.backend.features.category.domaine.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getImageUrl()
        );
    }

    public Category toEntity(CategoryRequest categoryRequest) {
        return new Category(
                null,
                categoryRequest.name(),
                categoryRequest.description(),
                categoryRequest.imageUrl()
        );
    }


}

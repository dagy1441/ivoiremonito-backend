package com.ivoiremonito.backend.features.category.service;

import com.ivoiremonito.backend.features.category.domaine.Category;
import com.ivoiremonito.backend.features.category.model.CategoryRequest;
import com.ivoiremonito.backend.features.category.model.CategoryResponse;

import java.util.List;

public interface CategoryService {

    /**
     * Get all categories
     * @return
     */
    List<CategoryResponse> getAll();

    /**
     * Get category by id
     * @param id
     * @return
     */
    CategoryResponse getById(Long id);

    /**
     * Create new category
     * @param categoryRequest
     * @return
     */
    CategoryResponse create(CategoryRequest categoryRequest);

    /**
     * Update category
     * @param id
     * @param categoryRequest
     * @return
     */
    CategoryResponse update(Long id, CategoryRequest categoryRequest);

    /**
     * Delete category
     * @param id
     */
    void delete(Long id);
}

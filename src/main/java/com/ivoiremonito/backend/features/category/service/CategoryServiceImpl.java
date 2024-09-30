package com.ivoiremonito.backend.features.category.service;

import com.ivoiremonito.backend.features.category.domaine.Category;
import com.ivoiremonito.backend.features.category.model.CategoryMapper;
import com.ivoiremonito.backend.features.category.model.CategoryRequest;
import com.ivoiremonito.backend.features.category.model.CategoryResponse;
import com.ivoiremonito.backend.features.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;
    final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAll() {
        log.info("Récupération de toutes les categories");

        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
    }

    @Override
    public CategoryResponse getById(Long id) {
        log.info("Récupération de la categorie par id {}", id);

        return categoryRepository.findById(id).map(categoryMapper::toResponse).orElse(null);
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        log.info("Sauvegarde d'une categorie : {}", categoryRequest);
        Category category = categoryMapper.toEntity(categoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        log.info("Modification d'une categorie : {}", categoryRequest);
        return categoryMapper.toResponse(categoryRepository.save(categoryMapper.toEntity(categoryRequest)));
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une categorie : {}", id);
        categoryRepository.deleteById(id);
    }
}

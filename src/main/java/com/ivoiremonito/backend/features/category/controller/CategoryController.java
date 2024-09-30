package com.ivoiremonito.backend.features.category.controller;

import com.ivoiremonito.backend.core.common.response.ApiResponse;
import com.ivoiremonito.backend.features.category.model.CategoryRequest;
import com.ivoiremonito.backend.features.category.model.CategoryResponse;
import com.ivoiremonito.backend.features.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryEndpoint{
    final CategoryService categoryService;
    @Override
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest categoryRequest) {

        log.info("Sauvegarde d'une categorie : {}", categoryRequest);

        CategoryResponse categoryResponse = categoryService.create(categoryRequest);

        ApiResponse<CategoryResponse> apiResponse = ApiResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sauvegarde reussie")
                .response(categoryResponse)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable Long id,@Valid @RequestBody CategoryRequest categoryRequest) {
        log.info("Modification d'une categorie : {}", categoryRequest);
        CategoryResponse categoryResponse = categoryService.update(id, categoryRequest);
        ApiResponse<CategoryResponse> apiResponse = ApiResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Modification reussie")
                .response(categoryResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public void delete(@PathVariable Long id) {

        log.info("Suppression d'une categorie : {}", id);
        categoryService.delete(id);
    }


    @Override
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id) {
        log.info("Récupération de la categorie par id {}", id);
        CategoryResponse categoryResponse = categoryService.getById(id);
        ApiResponse<CategoryResponse> apiResponse = ApiResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(categoryResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {

        log.info("Récupération de la liste des catégories");

        List<CategoryResponse> categoryResponse = categoryService.getAll();

        // Construction de la réponse en cas de succès
        ApiResponse<List<CategoryResponse>> apiResponse = ApiResponse.<List<CategoryResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche réussie")
                .response(categoryResponse)  // On utilise directement la liste
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}

package com.ivoiremonito.backend.features.category.repository;

import com.ivoiremonito.backend.features.category.domaine.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

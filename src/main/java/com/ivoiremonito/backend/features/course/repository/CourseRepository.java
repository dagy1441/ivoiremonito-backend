package com.ivoiremonito.backend.features.course.repository;

import com.ivoiremonito.backend.features.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCode(String code);
    Optional<Course> findByCodeAndIdNot(String code, Long id);
    List<Course> findByCategoryId(Long id);
    @Query("""
            SELECT course
            FROM Course course
            WHERE course.isActive = true
            """)
    Page<Course> getByCategoryIdAndActive(Long id, boolean active, Pageable pageable);
    boolean existsByCode(String code);

}

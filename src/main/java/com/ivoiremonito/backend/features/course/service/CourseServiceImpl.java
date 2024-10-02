package com.ivoiremonito.backend.features.course.service;

import com.ivoiremonito.backend.core.common.response.PageResponse;
import com.ivoiremonito.backend.core.exception.ResourceNotFoundException;
import com.ivoiremonito.backend.features.category.repository.CategoryRepository;
import com.ivoiremonito.backend.features.course.Course;
import com.ivoiremonito.backend.features.course.model.CourseMapper;
import com.ivoiremonito.backend.features.course.model.CourseRequest;
import com.ivoiremonito.backend.features.course.model.CourseResponse;
import com.ivoiremonito.backend.features.course.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    final CourseRepository courseRepository;
    final CategoryRepository categoryRepository;
    final CourseMapper courseMapper;

    @Override
    public CourseResponse create(CourseRequest courseRequest) {
        log.info("Sauvegarde d'un cours : {}", courseRequest);

        Course course = courseMapper.toEntity(courseRequest);
        course.setCode(new UUID(0, 8).toString());
        course.setCategory(categoryRepository.findById(courseRequest.categoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Todo non trouvé pour l'ID : " + courseRequest.categoryId())
        ));
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public CourseResponse update(CourseRequest courseRequest) {
        log.info("Modification d'un cours : {}", courseRequest);
        Course course = courseRepository.findByCode(courseRequest.code()).orElseThrow(
                () -> new ResourceNotFoundException("Cours non trouvé pour l'ID : " + courseRequest.code())
        );
        courseMapper.updateEntity(course, courseRequest);
        return courseMapper.toResponse(courseRepository.save(course));

    }

    @Override
    public CourseResponse getById(Long id) {
        log.info("Récupération du cours par id {}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cours non trouvé pour l'ID : " + id)
        );
        return courseMapper.toResponse(course);

    }

    @Override
    public List<CourseResponse> getAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getByCategory(Long id) {
        log.info("Récupération du cours par catégorie id {}", id);
        List<Course> courses = courseRepository.findByCategoryId(id);
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("Aucun cours trouvé pour la catégorie d'ID : " + id);
        }
        return courseMapper.toResponse(courses.get(0));
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un cours : {}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cours non trouvé pour l'ID : " + id)
        );
        courseRepository.delete(course);
    }

    @Override
    public PageResponse<CourseResponse> findAllCoursesByPage(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page et size doivent être positifs");
        }

        log.info("Récupération de la liste des cours paginée : page={}, size={}", page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Course> courses = courseRepository.findAll(pageable);

        List<CourseResponse> courseResponses = courses.getContent().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());

        return PageResponse.<CourseResponse>builder()
                .content(courseResponses)
                .number(courses.getNumber())
                .size(courses.getSize())
                .totalElements(courses.getTotalElements())
                .totalPages(courses.getTotalPages())
                .first(courses.isFirst())
                .last(courses.isLast())
                .build();
    }


    @Override
    public void activateCourse(Long id) {
        log.info("Activation / Désactivation d'un cours : {}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cours non trouvé pour l'ID : " + id)
        );
        course.setActive(!course.isActive());
        courseRepository.save(course);

    }

    @Override
    public PageResponse<CourseResponse> getByCategoryIdAndActive(Long id, boolean active, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page et size doivent être positifs");
        }

        log.info("Récupération de la liste des cours par catégorie id={} et actif={}", id, active);
        Pageable pageable = PageRequest.of(0, size, Sort.by("createdAt").descending());

        Page<Course> courses = courseRepository.getByCategoryIdAndActive(id, active, pageable);
        List<CourseResponse> courseResponses = courses.getContent().stream()
                .map(courseMapper::toResponse)
                .collect(Collectors.toList());
        return PageResponse.<CourseResponse>builder()
                .content(courseResponses)
                .number(courses.getNumber())
                .size(courses.getSize())
                .totalElements(courses.getTotalElements())
                .totalPages(courses.getTotalPages())
                .first(courses.isFirst())
                .last(courses.isLast())
                .build();

    }
}

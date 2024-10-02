package com.ivoiremonito.backend.features.course.controller;

import com.ivoiremonito.backend.core.common.response.ApiResponse;
import com.ivoiremonito.backend.features.course.model.CourseRequest;
import com.ivoiremonito.backend.features.course.model.CourseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseEndPoint {

    ResponseEntity<ApiResponse<CourseResponse>> create(CourseRequest courseRequest);

    ResponseEntity<ApiResponse<CourseResponse>> update(CourseRequest courseRequest);

    ResponseEntity<ApiResponse<CourseResponse>> getById(Long id);

    ResponseEntity<ApiResponse<CourseResponse>> getByCategory(Long id);

    ResponseEntity<ApiResponse<List<CourseResponse>>> getAll();

    void delete(Long id);

    ResponseEntity<ApiResponse<List<CourseResponse>>> findAllCoursesByPage(int page, int size);

    void activateCourse(Long id);

    ResponseEntity<ApiResponse<List<CourseResponse>>> getByCategoryIdAndActive(Long id, boolean active, int page, int size);
}

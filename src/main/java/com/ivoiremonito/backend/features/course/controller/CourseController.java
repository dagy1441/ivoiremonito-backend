package com.ivoiremonito.backend.features.course.controller;

import com.ivoiremonito.backend.core.common.response.ApiResponse;
import com.ivoiremonito.backend.core.common.response.PageResponse;
import com.ivoiremonito.backend.features.course.model.CourseRequest;
import com.ivoiremonito.backend.features.course.model.CourseResponse;
import com.ivoiremonito.backend.features.course.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Course")
@RequestMapping("/api/v1/courses")
@RestController
@RequiredArgsConstructor
public class CourseController implements CourseEndPoint {

    private final CourseService courseService;

    @Override
    public ResponseEntity<ApiResponse<CourseResponse>> create(CourseRequest courseRequest) {
        CourseResponse courseResponse = courseService.create(courseRequest);
        ApiResponse<CourseResponse> apiResponse = ApiResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Sauvegarde reussie")
                .response(courseResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }



    @Override
    public ResponseEntity<ApiResponse<CourseResponse>> update( CourseRequest courseRequest) {
        CourseResponse courseResponse = courseService.update( courseRequest);
        ApiResponse<CourseResponse> apiResponse = ApiResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Mise  jour reussie")
                .response(courseResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<CourseResponse>> getById(Long id) {
        CourseResponse courseResponse = courseService.getById(id);
        ApiResponse<CourseResponse> apiResponse = ApiResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(courseResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<CourseResponse>> getByCategory(Long id) {
        CourseResponse courseResponse = courseService.getByCategory(id);
        ApiResponse<CourseResponse> apiResponse = ApiResponse.<CourseResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(courseResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAll() {
        List<CourseResponse> courseResponses = courseService.getAll();
        ApiResponse<List<CourseResponse>> apiResponse = ApiResponse.<List<CourseResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(courseResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public void delete(Long id) {
        courseService.delete(id);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CourseResponse>>> findAllCoursesByPage(int page, int size) {
        PageResponse<CourseResponse> pageResponse = courseService.findAllCoursesByPage(page, size);
        ApiResponse<List<CourseResponse>> apiResponse = ApiResponse.<List<CourseResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(pageResponse.getContent())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public void activateCourse(Long id) {
        courseService.activateCourse(id);
    }

    @Override
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getByCategoryIdAndActive(Long id, boolean active, int page, int size) {
        PageResponse<CourseResponse> pageResponse = courseService.getByCategoryIdAndActive(id, active,  page,  size);
        ApiResponse<List<CourseResponse>> apiResponse = ApiResponse.<List<CourseResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recherche reussie")
                .response(pageResponse.getContent())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}

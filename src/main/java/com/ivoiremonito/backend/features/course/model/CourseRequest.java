package com.ivoiremonito.backend.features.course.model;

public record CourseRequest(
    String code,
    String description,
    String imageUrl,
    String videoUrl,
    String pdfUrl,
    boolean isFree,
    String price,
    String duration,
    String level,
    boolean isTop,
    Long categoryId
) {
}

package com.ivoiremonito.backend.features.course.model;

public record CourseResponse(
        String code,
        String description,
        String imageUrl,
        String videoUrl,
        String pdfUrl,
        boolean isFree,
        String price,
        String duration,
        String level,
        boolean isActive,
        boolean isTop
) {
}

package com.example.enrollment.Client;

import com.example.enrollment.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "course-service",
    url = "${services.course.url}"
)
public interface CourseClient {

    @GetMapping("/api/courses/{id}")
    CourseResponse getCourseById(
        @PathVariable("id") Long id
    );
}

package com.example.enrollment.Client;

import com.example.enrollment.dto.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "student-service",
    url = "${services.student.url}"
)
public interface StudentClient {

    @GetMapping("/api/students/{id}")
    StudentResponse getStudentById(
        @PathVariable("id") Long id
    );
}

package com.example.enrollment.controller;

import com.example.enrollment.dto.EnrollmentRequest;
import com.example.enrollment.dto.EnrollmentResponse;
import com.example.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(
            @Valid @RequestBody EnrollmentRequest request) {

        EnrollmentResponse response = enrollmentService.createEnrollment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> updateEnrollment(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequest request) {

        return ResponseEntity.ok(
                enrollmentService.updateEnrollment(
                        id,
                        request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        enrollmentService.deleteEnrollment(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteEnrollmentsByStudentId(
            @PathVariable Long studentId) {

        System.out.println("Enrollment Service: Deleting all enrollments for student ID: " + studentId);
        enrollmentService.deleteEnrollmentsByStudentId(studentId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteEnrollmentsByCourseId(
            @PathVariable Long courseId) {

        System.out.println("Enrollment Service: Deleting all enrollments for course ID: " + courseId);
        enrollmentService.deleteEnrollmentsByCourseId(courseId);

        return ResponseEntity.noContent().build();
    }
}
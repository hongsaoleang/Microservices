package com.example.enrollment.service;

import com.example.enrollment.dto.EnrollmentRequest;
import com.example.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse createEnrollment(
            EnrollmentRequest request);

    List<EnrollmentResponse> getAllEnrollments();

    EnrollmentResponse getEnrollmentById(Long id);

    EnrollmentResponse updateEnrollment(
            Long id,
            EnrollmentRequest request);

    void deleteEnrollment(Long id);

    void deleteEnrollmentsByStudentId(Long studentId);

    void deleteEnrollmentsByCourseId(Long courseId);
}
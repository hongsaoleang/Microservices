package com.example.enrollment.mapper;

import com.example.enrollment.dto.EnrollmentRequest;
import com.example.enrollment.dto.EnrollmentResponse;
import com.example.enrollment.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequest request) {

        Enrollment enrollment = new Enrollment();

        enrollment.setStudentId(request.getStudentId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStatus(request.getStatus());

        return enrollment;
    }

    public EnrollmentResponse toResponse(Enrollment enrollment) {

        EnrollmentResponse response = new EnrollmentResponse();

        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudentId());
        response.setCourseId(enrollment.getCourseId());
        response.setEnrollmentDate(
                enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus());

        return response;
    }

    public void updateEntity(
            Enrollment enrollment,
            EnrollmentRequest request) {

        enrollment.setStudentId(request.getStudentId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStatus(request.getStatus());
    }
}
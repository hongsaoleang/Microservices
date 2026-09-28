package com.example.enrollment.service;

import com.example.enrollment.dto.EnrollmentRequest;
import com.example.enrollment.dto.EnrollmentResponse;
import com.example.enrollment.entity.Enrollment;
import com.example.enrollment.exception.EnrollmentNotFoundException;
import com.example.enrollment.mapper.EnrollmentMapper;
import com.example.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    public EnrollmentResponse createEnrollment(
            EnrollmentRequest request) {

        Enrollment enrollment = enrollmentMapper.toEntity(request);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(savedEnrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponse)
                .toList();
    }

    @Override
    public EnrollmentResponse getEnrollmentById(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(
                        "Enrollment not found with id: " + id));

        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponse updateEnrollment(
            Long id,
            EnrollmentRequest request) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(
                        "Enrollment not found with id: " + id));

        enrollmentMapper.updateEntity(
                enrollment,
                request);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(
                updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(
                        "Enrollment not found with id: " + id));

        enrollmentRepository.delete(enrollment);
    }
}
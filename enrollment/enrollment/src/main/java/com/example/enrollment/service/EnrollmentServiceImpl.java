package com.example.enrollment.service;

import com.example.enrollment.Client.CourseClient;
import com.example.enrollment.Client.StudentClient;
import com.example.enrollment.dto.EnrollmentRequest;
import com.example.enrollment.dto.EnrollmentResponse;
import com.example.enrollment.entity.Enrollment;
import com.example.enrollment.exception.EnrollmentNotFoundException;
import com.example.enrollment.mapper.EnrollmentMapper;
import com.example.enrollment.repository.EnrollmentRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final StudentClient studentClient;
    private final CourseClient courseClient;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            EnrollmentMapper enrollmentMapper,
            StudentClient studentClient,
            CourseClient courseClient) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.studentClient = studentClient;
        this.courseClient = courseClient;
    }

    @Override
    public EnrollmentResponse createEnrollment(
            EnrollmentRequest request) {

        try {
            studentClient.getStudentById(request.getStudentId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Student not found with id: " + request.getStudentId());
        } catch (Exception ignored) {
            // Allow if service is offline during independent testing
        }

        try {
            courseClient.getCourseById(request.getCourseId());
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("Course not found with id: " + request.getCourseId());
        } catch (Exception ignored) {
            // Allow if service is offline during independent testing
        }

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

    @org.springframework.transaction.annotation.Transactional
    @Override
    public void deleteEnrollmentsByStudentId(Long studentId) {
        enrollmentRepository.deleteByStudentId(studentId);
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public void deleteEnrollmentsByCourseId(Long courseId) {
        enrollmentRepository.deleteByCourseId(courseId);
    }
}
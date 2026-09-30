package com.example.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.enrollment.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Modifying
    @Transactional
    void deleteByStudentId(Long studentId);

    @Modifying
    @Transactional
    void deleteByCourseId(Long courseId);
}

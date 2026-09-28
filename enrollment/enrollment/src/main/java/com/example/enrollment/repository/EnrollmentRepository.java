package com.example.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.enrollment.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    
}

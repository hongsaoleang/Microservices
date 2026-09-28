package com.example.enrollment.exception;

public class EnrollmentNotFoundException
        extends RuntimeException {

    public EnrollmentNotFoundException(String message) {
        super(message);
    }
}
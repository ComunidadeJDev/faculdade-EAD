package com.jdev.student.exceptions.customizeExceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found exception!");
    }
}

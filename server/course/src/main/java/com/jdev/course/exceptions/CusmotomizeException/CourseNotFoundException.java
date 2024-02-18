package com.jdev.course.exceptions.CusmotomizeException;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course Not found!");
    }
}

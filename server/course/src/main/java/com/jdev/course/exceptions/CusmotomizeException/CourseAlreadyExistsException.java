package com.jdev.course.exceptions.CusmotomizeException;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException() {
        super("course already exists!");
    }
}

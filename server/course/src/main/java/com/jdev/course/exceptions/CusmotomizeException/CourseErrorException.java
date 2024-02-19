package com.jdev.course.exceptions.CusmotomizeException;

public class CourseErrorException extends RuntimeException {
    public CourseErrorException(String error) {
        super(error);
    }
}

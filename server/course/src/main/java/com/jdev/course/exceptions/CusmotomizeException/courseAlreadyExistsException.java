package com.jdev.course.exceptions.CusmotomizeException;

public class courseAlreadyExistsException extends RuntimeException {
    public courseAlreadyExistsException() {
        super("course already exists!");
    }
}

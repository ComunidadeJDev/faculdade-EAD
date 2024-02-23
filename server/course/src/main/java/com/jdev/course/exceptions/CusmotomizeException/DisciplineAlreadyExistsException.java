package com.jdev.course.exceptions.CusmotomizeException;

public class DisciplineAlreadyExistsException extends RuntimeException {
    public DisciplineAlreadyExistsException() {
        super("Module already exists!");
    }
}

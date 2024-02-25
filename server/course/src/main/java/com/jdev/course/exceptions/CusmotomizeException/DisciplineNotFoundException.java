package com.jdev.course.exceptions.CusmotomizeException;

public class DisciplineNotFoundException extends RuntimeException {
    public DisciplineNotFoundException() {
        super("Discipline not found!");
    }
}

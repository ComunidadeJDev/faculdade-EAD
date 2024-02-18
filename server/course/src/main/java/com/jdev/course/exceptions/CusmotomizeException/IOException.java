package com.jdev.course.exceptions.CusmotomizeException;

public class IOException extends RuntimeException {
    public IOException() {
        super("Error write file in directory");
    }
}

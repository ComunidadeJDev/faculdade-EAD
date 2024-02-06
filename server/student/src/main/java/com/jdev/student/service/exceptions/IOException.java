package com.jdev.student.service.exceptions;

public class IOException extends RuntimeException {
    public IOException() {
        super("Error write file in directory");
    }
}

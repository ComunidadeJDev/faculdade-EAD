package com.jdev.student.exceptions.customizeExceptions;

public class IOException extends RuntimeException {
    public IOException() {
        super("Error write file in directory");
    }
}

package com.jdev.course.exceptions.customizeExceptions;

public class FileErrorException extends RuntimeException {
    public FileErrorException(String error) {
        super(error);
    }
}

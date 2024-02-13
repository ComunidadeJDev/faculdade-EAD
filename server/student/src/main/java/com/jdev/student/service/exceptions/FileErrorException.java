package com.jdev.student.service.exceptions;

public class FileErrorException extends RuntimeException {
    public FileErrorException(String error) {
        super(error);
    }
}

package com.jdev.course.exceptions.CusmotomizeException;

public class FileErrorException extends RuntimeException {
    public FileErrorException(String error) {
        super(error);
    }
}

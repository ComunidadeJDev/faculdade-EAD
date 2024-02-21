package com.jdev.course.exceptions.CusmotomizeException;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException() {
        super("Invalid file format!");
    }
}

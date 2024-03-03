package com.jdev.student.exceptions.customizeExceptions;

public class ErrorReadResponseOfTheRequestException extends RuntimeException {
    public ErrorReadResponseOfTheRequestException(String error) {
        super(error);
    }
}

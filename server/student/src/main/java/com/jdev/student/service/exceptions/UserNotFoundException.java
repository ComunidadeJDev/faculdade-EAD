package com.jdev.student.service.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}

package com.jdev.student.exceptions.customizeExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}

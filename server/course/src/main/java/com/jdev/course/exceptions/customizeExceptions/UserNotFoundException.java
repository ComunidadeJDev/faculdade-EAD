package com.jdev.course.exceptions.customizeExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}

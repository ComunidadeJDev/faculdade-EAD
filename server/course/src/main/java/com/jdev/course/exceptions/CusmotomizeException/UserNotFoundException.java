package com.jdev.course.exceptions.CusmotomizeException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}

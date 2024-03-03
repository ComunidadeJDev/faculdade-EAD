package com.jdev.student.exceptions.customizeExceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("image not found!");
    }
}

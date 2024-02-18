package com.jdev.course.exceptions.customizeExceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("image not found!");
    }
}

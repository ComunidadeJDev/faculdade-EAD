package com.jdev.student.service.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("image not found!");
    }
}

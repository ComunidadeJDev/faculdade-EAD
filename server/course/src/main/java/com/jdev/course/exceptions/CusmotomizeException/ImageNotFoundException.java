package com.jdev.course.exceptions.CusmotomizeException;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super("image not found!");
    }
}

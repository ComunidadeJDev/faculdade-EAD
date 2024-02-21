package com.jdev.course.exceptions.CusmotomizeException;

public class MaterialNotFoundException extends RuntimeException {
    public MaterialNotFoundException() {
        super("material not found!");
    }
}

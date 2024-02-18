package com.jdev.course.exceptions.CusmotomizeException;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("module not found!");
    }
}

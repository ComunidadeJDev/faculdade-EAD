package com.jdev.course.exceptions.CusmotomizeException;

public class ModuleAlreadyExistsException extends RuntimeException {
    public ModuleAlreadyExistsException() {
        super("Module already exists!");
    }
}

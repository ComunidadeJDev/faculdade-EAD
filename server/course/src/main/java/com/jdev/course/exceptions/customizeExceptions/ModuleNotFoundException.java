package com.jdev.course.exceptions.customizeExceptions;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("module not found!");
    }
}

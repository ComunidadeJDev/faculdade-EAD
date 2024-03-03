package com.jdev.student.exceptions.customizeExceptions;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("module not found!");
    }
}

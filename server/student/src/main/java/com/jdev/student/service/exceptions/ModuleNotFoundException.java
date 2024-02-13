package com.jdev.student.service.exceptions;

public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("module not found!");
    }
}

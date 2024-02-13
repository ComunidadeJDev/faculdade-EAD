package com.jdev.student.service.exceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException() {
        super("file null content!");
    }
}

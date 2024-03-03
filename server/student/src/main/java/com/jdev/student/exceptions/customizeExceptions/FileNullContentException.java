package com.jdev.student.exceptions.customizeExceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException() {
        super("file null content!");
    }
}

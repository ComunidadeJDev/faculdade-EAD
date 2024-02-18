package com.jdev.course.exceptions.customizeExceptions;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException() {
        super("file null content!");
    }
}

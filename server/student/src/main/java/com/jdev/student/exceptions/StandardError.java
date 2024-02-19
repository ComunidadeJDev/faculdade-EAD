package com.jdev.student.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timeStamp;
    private Integer status;
    private String fieldError;
    private String path;
    private String error;
}

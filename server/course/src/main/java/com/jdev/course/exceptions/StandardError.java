package com.jdev.course.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timeStamp;
    private Integer status;
    private String fieldError;
    private String path;
    private String error;
}

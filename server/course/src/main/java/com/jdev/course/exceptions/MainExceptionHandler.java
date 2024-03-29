package com.jdev.course.exceptions;

import com.jdev.course.exceptions.CusmotomizeException.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.LocalDateTime;

@ControllerAdvice
public class MainExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<StandardError> runtimeException(RuntimeException ex, HttpServletRequest request) {
//        StandardError error = StandardError.builder()
//                .timeStamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .fieldError(ex.getMessage())
//                .path(request.getRequestURI())
//                .error("RuntimeException")
//                .build();
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldError(ex.getMessage())
                .path(request.getRequestURI())
                .error("ObjectNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .fieldError(ex.getBindingResult().getFieldError().getField() + " " + ex.getFieldError().getDefaultMessage())
                .path(request.getRequestURI())
                .error("MethodArgumentNotValidException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .fieldError(ex.getMessage())
                .path(request.getRequestURI())
                .error("ConstraintViolationException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getMessage())
                .path(request.getRequestURI())
                .error("HttpMessageNotReadableException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getMostSpecificCause().getMessage())
                .path(request.getRequestURI())
                .error("DataIntegrityViolationException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> transactionSystemException(TransactionSystemException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("TransactionSystemException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> userNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("UserNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<StandardError> missingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("MissingServletRequestPartException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<StandardError> courseAlreadyExistsException(CourseAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("CourseAlreadyExistsException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseErrorException.class)
    public ResponseEntity<StandardError> courseErrorException(CourseErrorException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("CourseErrorException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<StandardError> courseNotFoundException(CourseNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("CourseNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNullContentException.class)
    public ResponseEntity<StandardError> fileNullContentException(FileNullContentException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("FileNullContentException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisciplineNotFoundException.class)
    public ResponseEntity<StandardError> ModuleNotFoundException(DisciplineNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("ModuleNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("MethodArgumentTypeMismatchException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaterialNotFoundException.class)
    public ResponseEntity<StandardError> materialNotFoundException(MaterialNotFoundException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("MaterialNotFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<StandardError> multipartException(MultipartException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("MultipartException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileErrorException.class)
    public ResponseEntity<StandardError> fileErrorException(FileErrorException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("FileErrorException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<StandardError> invalidFileFormatException(InvalidFileFormatException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("InvalidFileFormatException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisciplineAlreadyExistsException.class)
    public ResponseEntity<StandardError> moduleAlreadyExistsException(DisciplineAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("ModuleAlreadyExistsException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThemeException.class)
    public ResponseEntity<StandardError> themeException(ThemeException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("ThemeException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorSendCourseForStudentException.class)
    public ResponseEntity<StandardError> errorSendCourseForStudentException(ErrorSendCourseForStudentException ex, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fieldError(ex.getLocalizedMessage())
                .path(request.getRequestURI())
                .error("ErrorSendCourseForStudentException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

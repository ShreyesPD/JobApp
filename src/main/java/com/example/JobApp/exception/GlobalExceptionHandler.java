package com.example.JobApp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        JobPostException error = new JobPostException(
                404,
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                "Required Resource Not Found, hence the operation was unsuccessful!!"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        JobPostException error = new JobPostException(
                400,
                HttpStatus.BAD_REQUEST,
                "Please make sure you send data in proper format, also don't send an empty request body!!",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // NoResourceFoundException
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex) {
        JobPostException error = new JobPostException(
                400,
                HttpStatus.BAD_REQUEST,
                "Check proper format of the api and its Http method!!",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // INVALID FIELD DATA
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> res = new HashMap<>();

        ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((er) -> {
            String fieldName = ((FieldError) er).getField();
            String message = er.getDefaultMessage();
            res.put(fieldName, message);
        });
        JobPostException error = new JobPostException(
                400,
                HttpStatus.BAD_REQUEST,
                "Invalid Inputs !!",
                res.toString()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // DataIntegrityViolationException
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        JobPostException error = new JobPostException(
                400,
                HttpStatus.BAD_REQUEST,
                "User already exists with the entered email !!",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // MethodArgumentTypeMismatchException
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        JobPostException error = new JobPostException(
                400,
                HttpStatus.BAD_REQUEST,
                "Please provide proper Integer ID input in the api url argument!!",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // UN IDENTIFIED EXCEPTION
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleUnidentifiedException(Exception ex) {
        JobPostException error = new JobPostException(
                500,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Un identified error | Please contact the Backend Developer!!... :D",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

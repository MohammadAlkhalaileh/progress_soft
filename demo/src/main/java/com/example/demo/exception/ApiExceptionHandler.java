package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Date;
import java.text.SimpleDateFormat;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Date date = new Date(System.currentTimeMillis());
        ApiException apiException = new ApiException(e.getMessage(), httpStatus, formatter.format(date));

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Date date = new Date(System.currentTimeMillis());
        ApiException apiException = new ApiException(e.getMessage(), httpStatus, formatter.format(date));

        return new ResponseEntity<>(apiException, httpStatus);
    }
}

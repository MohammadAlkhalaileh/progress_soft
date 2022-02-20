package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;
    private final String timestamp;

    public ApiException(String message, HttpStatus httpStatus, String timestamp) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

}

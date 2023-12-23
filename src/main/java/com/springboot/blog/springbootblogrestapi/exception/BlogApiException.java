package com.springboot.blog.springbootblogrestapi.exception;

import org.springframework.http.HttpStatus;

import io.micrometer.core.ipc.http.HttpSender;

/**
 * BlogApiException
 */
public class BlogApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public BlogApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
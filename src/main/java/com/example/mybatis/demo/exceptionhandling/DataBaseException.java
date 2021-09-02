package com.example.mybatis.demo.exceptionhandling;

import org.springframework.http.HttpStatus;

public class DataBaseException extends Exception {
    private final HttpStatus responseCode;

    public DataBaseException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}

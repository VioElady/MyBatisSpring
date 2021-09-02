package com.example.mybatis.demo.exceptionhandling;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException{
    private final HttpStatus responseCode;

    public ProductNotFoundException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}

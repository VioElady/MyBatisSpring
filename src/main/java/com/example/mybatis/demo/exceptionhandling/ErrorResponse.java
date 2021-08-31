package com.example.mybatis.demo.exceptionhandling;

import java.util.Date;

public class ErrorResponse {
    private String message;
    private Date time;

    ErrorResponse(String message) {
        this.message = message;
        this.time = new Date();
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }
}
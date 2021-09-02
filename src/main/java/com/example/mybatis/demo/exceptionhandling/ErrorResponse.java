package com.example.mybatis.demo.exceptionhandling;

import lombok.Getter;

import java.util.Date;
@Getter

public class ErrorResponse {
    private String message;
    private Date time;

    ErrorResponse(String message) {
        this.message = message;
        this.time = new Date();
    }

}
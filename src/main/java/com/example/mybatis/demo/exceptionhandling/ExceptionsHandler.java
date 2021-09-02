package com.example.mybatis.demo.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<Object> handleCustomExceptions(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()),
                ((DataBaseException) exception).getResponseCode());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundMovie(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()),
                ((ProductNotFoundException) exception).getResponseCode());
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), METHOD_NOT_ALLOWED);
        } else if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(new ErrorResponse("A null value has created an issue, the request could not be performed"), BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), BAD_GATEWAY);
    }

}
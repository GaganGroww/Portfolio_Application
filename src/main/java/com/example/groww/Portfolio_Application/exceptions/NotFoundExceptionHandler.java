package com.example.groww.Portfolio_Application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<String> handle(String message){
        return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
    }
}

package com.example.myedu.exception;

import com.example.myedu.model.response.MessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handlerException(CustomException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage()), e.getHttpStatus());
    }
}

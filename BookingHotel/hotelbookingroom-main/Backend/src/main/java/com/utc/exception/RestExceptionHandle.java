package com.utc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handelEntityNotFound(UsernameNotFoundException e){
        return ResponseEntity.status(401).body("User dose not exists");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelBackEndError(Exception e){
        return ResponseEntity.status(500).body("Back end error!!!");
    }
}

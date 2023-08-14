package com.leon.gptclone.exceptionHandler;

import com.leon.gptclone.model.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> duplicatedUsername(HttpServletRequest request, DataIntegrityViolationException e){
        return new ResponseEntity<>(new Response("0008", "New username is duplicated!"), HttpStatus.BAD_REQUEST);
    }
}

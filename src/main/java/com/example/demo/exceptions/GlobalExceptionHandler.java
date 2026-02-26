package com.example.demo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({LiberNotFoundException.class, IllegalArgumentException.class})
    public void handleLiberNotFoundException(Exception e) {
        System.out.printf("Gjate nderveprimit me sistemin ndodhi nje problem");
        System.out.printf(e.getMessage());
        System.out.printf("Problemi u identifikua");
    }

    @ExceptionHandler(LiberIsbnExistsException.class)
    public void handleLiberIsbnExistsException(Exception e) {
        System.out.printf("Gjate nderveprimit me librat, u identifikua nje problem");
        System.out.printf(e.getMessage());
        System.out.printf("Problemi u identifikua");
    }

}

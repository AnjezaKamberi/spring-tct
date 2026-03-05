package com.example.demo.exceptions;

import com.example.demo.models.ApiResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({LiberNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResponseError> handleLiberNotFoundException(Exception e) {
        log.info("Gjate nderveprimit me sistemin ndodhi nje problem");
        log.info(e.getMessage());
        log.info("Problemi u identifikua");
        ApiResponseError apiResponseError = new ApiResponseError(e.getMessage(), LocalDateTime.now(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponseError);
    }

    @ExceptionHandler(LiberIsbnExistsException.class)
    public ResponseEntity<ApiResponseError> handleLiberIsbnExistsException(Exception e) {
        log.info("Gjate nderveprimit me librat, u identifikua nje problem");
        log.info(e.getMessage());
        log.info("Problemi u identifikua");
        ApiResponseError apiResponseError = new ApiResponseError(e.getMessage(), LocalDateTime.now(), 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseError);
    }

}

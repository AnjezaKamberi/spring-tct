package com.example.demo.models;

import java.time.LocalDateTime;

public class ApiResponseError {

    private String message;
    private LocalDateTime createdAt;
    private int statucCode;

    public ApiResponseError(String message, LocalDateTime createdAt, int statucCode) {
        this.message = message;
        this.createdAt = createdAt;
        this.statucCode = statucCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getStatucCode() {
        return statucCode;
    }

    public void setStatucCode(int statucCode) {
        this.statucCode = statucCode;
    }
}

package com.example.recipeback.exception;

public class CustomJwtExpiredException extends RuntimeException {
    public CustomJwtExpiredException(String message) {
        super(message);
    }
}


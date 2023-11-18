package com.example.recipeback.exception;

public class EntityAlreadyExistException extends RuntimeException {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
package com.ivoiremonito.backend.core.exception;

public class EntityAllReadyExistException extends RuntimeException {
    public EntityAllReadyExistException(String message) {
        super(message);
    }
}
package com.sayed.seu.backend.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message) {
        super(message + " not found");
    }
}

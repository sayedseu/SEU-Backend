package com.sayed.seu.backend.exception;

public class ResourceAlreadyExistException extends Exception {
    public ResourceAlreadyExistException(String message) {
        super(message + " already exist");
    }
}

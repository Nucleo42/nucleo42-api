package com.nucleo42.exception;

public class RegisterDoesNotExistsException extends RuntimeException {
    public RegisterDoesNotExistsException(String message) {
        super(message);
    }
}

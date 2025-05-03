package com.nucleo42.exception;

public class RegisterAlreadyExistsException extends RuntimeException {
    public RegisterAlreadyExistsException(String message) {
        super(message);
    }
}

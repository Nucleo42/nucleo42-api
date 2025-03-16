package com.nucleo42.exception;

public class UserIdIsNullException extends RuntimeException {
    public UserIdIsNullException() {
        super("User id cannot be null");
    }

}

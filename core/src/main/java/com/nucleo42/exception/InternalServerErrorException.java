package com.nucleo42.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("Internal error while trying to register the project. Please try again later.");
    }
}

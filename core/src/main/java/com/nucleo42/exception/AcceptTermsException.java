package com.nucleo42.exception;

public class AcceptTermsException extends RuntimeException{
    public AcceptTermsException() {
        super("You must accept the terms and conditions");
    }
}

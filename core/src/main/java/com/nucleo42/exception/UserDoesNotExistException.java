package com.nucleo42.exception;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(){
        super("User does not exist");
    }
    
}

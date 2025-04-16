package com.nucleo42.exception;

public class UserIdIsInvalidException extends RuntimeException{
    public UserIdIsInvalidException(String id){
        super("User id '"+id+"' is invalid");
    }
    
}

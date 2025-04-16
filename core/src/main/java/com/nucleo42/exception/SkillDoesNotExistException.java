package com.nucleo42.exception;

public class SkillDoesNotExistException extends RuntimeException{
    public SkillDoesNotExistException(long id){
        super("Skill id '"+id+"' does not exist");
    }
}

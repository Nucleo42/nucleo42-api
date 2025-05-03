package com.nucleo42.infrastructure.dto;

import org.springframework.validation.FieldError;

public record ResponseFieldErrorDTO(String field, String error) {

    public static ResponseFieldErrorDTO fromValidation(FieldError fieldError)
    {
        return new ResponseFieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
    }
}

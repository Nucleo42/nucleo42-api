package com.nucleo42.infrastructure.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ResponseErrorDTO(int status, String message, List<ResponseFieldErrorDTO> errors) {

    public static ResponseErrorDTO send(String message)
    {
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ResponseErrorDTO conflict(String message)
    {
        return new ResponseErrorDTO(HttpStatus.CONFLICT.value(), message, List.of());
    }

}


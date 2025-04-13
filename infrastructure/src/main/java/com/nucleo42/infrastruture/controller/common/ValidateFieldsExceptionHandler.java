package com.nucleo42.infrastruture.controller.common;

import com.nucleo42.infrastruture.dto.ResponseErrorDTO;
import com.nucleo42.infrastruture.dto.ResponseFieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice()
public class ValidateFieldsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        List<FieldError> fieldErrors = exception.getFieldErrors();

        List<ResponseFieldErrorDTO> response = fieldErrors.stream().map(ResponseFieldErrorDTO::fromValidation).toList();

        return new ResponseErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Campos inválidos", response);
    }
}

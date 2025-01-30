package com.nucleo42.dto.response;

import java.util.List;

public record ErrorResponse (
    String code,
    String message,
    List<ValidationError> validitions
){
    
}

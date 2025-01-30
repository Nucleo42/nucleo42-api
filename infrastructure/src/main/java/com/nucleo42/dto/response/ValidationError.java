package com.nucleo42.dto.response;

public record ValidationError(
    String field,
    String message
) {
    
}

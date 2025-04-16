package com.nucleo42.infrastructure.exception;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.WebUtils;

import com.nucleo42.exception.SkillDoesNotExistException;
import com.nucleo42.exception.UserDoesNotExistException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UserDoesNotExistException.class,
            SkillDoesNotExistException.class,
    })
    public final ResponseEntity<List<String>> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UserDoesNotExistException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserDoesNotExistException unfe = (UserDoesNotExistException) ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        }

        if (ex instanceof SkillDoesNotExistException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            SkillDoesNotExistException snf = (SkillDoesNotExistException) ex;

            return handleSkillNotFoundException(snf, headers, status, request);
        }

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<List<String>> handleUserNotFoundException(UserDoesNotExistException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    protected ResponseEntity<List<String>> handleSkillNotFoundException(SkillDoesNotExistException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, errors, headers, status, request);
    }

    protected ResponseEntity<List<String>> handleExceptionInternal(Exception ex, @Nullable List<String> body,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult().getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.joining("\n")));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("User id '" + ex.getValue() + "' is invalid");
    }
}
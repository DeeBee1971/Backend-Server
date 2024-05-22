package com.example.demo.ExceptionHandling;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvoiceNotFound.class)
    public ResponseEntity<Object> handleInvoiceNotFoundExceptions(InvoiceNotFound ex ) {
		
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setErrorCode(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
	
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFound ex ) {
    	
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setDateTime(LocalDateTime.now());
        response.setErrorCode(404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
}

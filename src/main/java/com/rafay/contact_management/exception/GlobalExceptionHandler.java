package com.rafay.contact_management.exception;


import com.rafay.contact_management.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
        ErrorResponse error = new ErrorResponse(404,ex.getMessage());
        return  ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceException ex){
        ErrorResponse error = new ErrorResponse(409,ex.getMessage());
        return  ResponseEntity.status(409).body(error);
    }
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedAccessException ex){
        ErrorResponse error = new ErrorResponse(403,ex.getMessage());
        return  ResponseEntity.status(403).body(error);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex){
        ErrorResponse error = new ErrorResponse(500,ex.getMessage());
        return  ResponseEntity.status(500).body(error);
    }


}

package com.fatma.movie_app.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecordNotCorrectException.class)
    public ResponseEntity<?> RecordNotCorrectException(RecordNotCorrectException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> RecordNotFoundException(RecordNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<?> DuplicateRecordException(DuplicateRecordException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handelException(Exception ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}

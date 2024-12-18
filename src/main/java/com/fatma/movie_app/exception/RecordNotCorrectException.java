package com.fatma.movie_app.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class RecordNotCorrectException extends RuntimeException {
    public RecordNotCorrectException(){
        super();
    }
    public RecordNotCorrectException(String message){
        super(message);
    }

}

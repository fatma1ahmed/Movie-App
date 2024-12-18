package com.fatma.movie_app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private LocalDateTime dateTime;
    private boolean success;

    public ErrorResponse(String message){
        this.message=message;
        this.dateTime=LocalDateTime.now();
        this.success=Boolean.FALSE;
    }
}

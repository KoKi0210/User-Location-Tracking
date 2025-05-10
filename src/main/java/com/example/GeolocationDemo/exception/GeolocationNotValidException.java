package com.example.GeolocationDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeolocationNotValidException extends RuntimeException{
    public GeolocationNotValidException(String message) {
        super(message);
    }
}

package com.example.GeolocationDemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeolocationNotFoundException extends RuntimeException{
    public GeolocationNotFoundException() {
        super(ErrorMessages.GEOLOCATION_NOT_FOUND.getMessage());
    }
}

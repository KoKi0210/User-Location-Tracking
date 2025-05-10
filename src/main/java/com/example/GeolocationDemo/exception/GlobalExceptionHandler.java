package com.example.GeolocationDemo.exception;

import com.example.GeolocationDemo.geolocation.model.ErrorResponse;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeolocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleGeolocationNotFoundException(GeolocationNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException exception){
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(GeolocationNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleGeolocationNotValidException(GeolocationNotValidException exception){
        return new ErrorResponse(exception.getMessage());
    }
}

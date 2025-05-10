package com.example.GeolocationDemo.exception;

public enum ErrorMessages {
    GEOLOCATION_NOT_FOUND("Geolocation not found"),
    USER_NOT_FOUND("User not found id:"),
    INVALID_LATITUDE("Latitude must be between -90 and 90"),
    INVALID_LONGITUDE("Longitude must be between -180 and 180");

    private final String message;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

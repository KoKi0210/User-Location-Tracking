package com.example.GeolocationDemo.geolocation.validators;

import com.example.GeolocationDemo.exception.ErrorMessages;
import com.example.GeolocationDemo.exception.GeolocationNotValidException;
import com.example.GeolocationDemo.geolocation.model.Geolocation;

public class GeolocationValidator {

    private GeolocationValidator(){

    }

    public static void execute(Geolocation geolocation){
        if (geolocation.getLatitude() < -90 || geolocation.getLatitude()> 90){
            throw new GeolocationNotValidException(ErrorMessages.INVALID_LATITUDE.getMessage());
        }

        if (geolocation.getLongitude() < -180 || geolocation.getLongitude() > 180){
            throw new GeolocationNotValidException(ErrorMessages.INVALID_LONGITUDE.getMessage());
        }
    }
}

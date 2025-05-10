package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Command;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import com.example.GeolocationDemo.geolocation.validators.GeolocationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateGeolocationService implements Command<Geolocation, GeolocationDTO> {

    private final GeolocationRepository geolocationRepository;

    public CreateGeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<GeolocationDTO> execute(Geolocation geolocation) {

        GeolocationValidator.execute(geolocation);

        Geolocation savedGeolocation = geolocationRepository.save(geolocation);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GeolocationDTO(savedGeolocation));
    }
}

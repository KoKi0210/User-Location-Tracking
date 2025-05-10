package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Command;
import com.example.GeolocationDemo.exception.GeolocationNotFoundException;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteGeolocationService implements Command<Integer, Void> {

    private final GeolocationRepository geolocationRepository;

    public DeleteGeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Geolocation> geolocationOptional = geolocationRepository.findById(id);
        if (geolocationOptional.isPresent()) {
            geolocationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new GeolocationNotFoundException();
    }
}

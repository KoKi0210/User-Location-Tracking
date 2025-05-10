package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Query;
import com.example.GeolocationDemo.exception.GeolocationNotFoundException;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetGeolocationService implements Query<Integer, GeolocationDTO> {

    private final GeolocationRepository geolocationRepository;

    public GetGeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<GeolocationDTO> execute(Integer id) {

        Optional<Geolocation> optionalGeolocation = geolocationRepository.findById(id);

        if (optionalGeolocation.isPresent()){
            return ResponseEntity.ok(new GeolocationDTO(optionalGeolocation.get()));
        }

        throw new GeolocationNotFoundException();
    }
}

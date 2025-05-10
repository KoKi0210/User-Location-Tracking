package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Query;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetGeolocationsService implements Query<Void, List<GeolocationDTO>> {

    private final GeolocationRepository geolocationRepository;

    public GetGeolocationsService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<List<GeolocationDTO>> execute(Void input) {
        List<Geolocation> geolocations = geolocationRepository.findAll();
        List<GeolocationDTO> geolocationDTOS = geolocations.stream().map(GeolocationDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(geolocationDTOS);
    }
}

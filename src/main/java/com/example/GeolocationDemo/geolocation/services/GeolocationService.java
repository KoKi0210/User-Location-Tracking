package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GeolocationService {

    ResponseEntity<GeolocationDTO> createGeolocation(Geolocation geolocation);

    ResponseEntity<Void> deleteGeolocation(Integer id);

    ResponseEntity<Geolocation> getGeolocationByUserId(Integer userId);

    ResponseEntity<List<Geolocation>> getGeolocationsByUserId(Integer id);

    ResponseEntity<GeolocationDTO> getGeolocation(Integer id);

    ResponseEntity<List<GeolocationDTO>> getGeolocations();

    ResponseEntity<String> updateGeolocation(Integer id, Geolocation geolocation);
}

package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Query;
import com.example.GeolocationDemo.exception.UserNotFoundException;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetGeolocationByUserIdService implements Query<Integer, Geolocation> {
    private final GeolocationRepository geolocationRepository;

    public GetGeolocationByUserIdService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<Geolocation> execute(Integer userId) {
        List<Geolocation> optionalGeolocation = geolocationRepository.getByUserId(userId);

        if (!optionalGeolocation.isEmpty()) {
            List<Geolocation> geolocations = optionalGeolocation.stream()
                    .sorted(Comparator.comparing(Geolocation::getCreateDate))
                    .toList();

            Geolocation newestGeolocation = geolocations.get(geolocations.size()- 1);
            return ResponseEntity.ok(newestGeolocation);

        }
        throw new UserNotFoundException(userId);
    }
}

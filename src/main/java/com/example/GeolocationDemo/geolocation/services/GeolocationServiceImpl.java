package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.exception.GeolocationNotFoundException;
import com.example.GeolocationDemo.exception.UserNotFoundException;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import com.example.GeolocationDemo.geolocation.validators.GeolocationValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class GeolocationServiceImpl implements GeolocationService {

    private final GeolocationRepository geolocationRepository;

    public GeolocationServiceImpl(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<GeolocationDTO> createGeolocation(Geolocation geolocation) {
        GeolocationValidator.execute(geolocation);

        Geolocation savedGeolocation = geolocationRepository.save(geolocation);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GeolocationDTO(savedGeolocation));
    }

    @Override
    public ResponseEntity<Void> deleteGeolocation(Integer id) {
        Optional<Geolocation> geolocationOptional = geolocationRepository.findById(id);
        if (geolocationOptional.isPresent()) {
            geolocationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new GeolocationNotFoundException();
    }

    @Override
    public ResponseEntity<Geolocation> getGeolocationByUserId(Integer userId) {
        List<Geolocation> optionalGeolocation = geolocationRepository.getByUserId(userId);

        if (!optionalGeolocation.isEmpty()) {
            List<Geolocation> geolocations = optionalGeolocation.stream()
                    .sorted(Comparator.comparing(Geolocation::getCreateDate))
                    .toList();

            Geolocation newestGeolocation = geolocations.get(geolocations.size() - 1);
            return ResponseEntity.ok(newestGeolocation);

        }
        throw new UserNotFoundException(userId);
    }

    @Override
    public ResponseEntity<GeolocationDTO> getGeolocation(Integer id) {
        Optional<Geolocation> optionalGeolocation = geolocationRepository.findById(id);

        if (optionalGeolocation.isPresent()) {
            return ResponseEntity.ok(new GeolocationDTO(optionalGeolocation.get()));
        }

        throw new GeolocationNotFoundException();
    }

    @Override
    public ResponseEntity<List<GeolocationDTO>> getGeolocations() {
        List<Geolocation> geolocations = geolocationRepository.findAll();
        List<GeolocationDTO> geolocationDTOS = geolocations.stream().map(GeolocationDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(geolocationDTOS);
    }

    @Override
    public ResponseEntity<String> updateGeolocation(Integer id, Geolocation geolocation) {
        Optional<Geolocation> optionalGeolocation = geolocationRepository.findById(id);
        if (optionalGeolocation.isPresent()) {
            Geolocation updatedGeolocation;
            updatedGeolocation = geolocation;
            updatedGeolocation.setId(id);
            GeolocationValidator.execute(updatedGeolocation);
            geolocationRepository.save(updatedGeolocation);
            return ResponseEntity.ok("Успешна промяна на геолокация! ID: " + id);
        }
        throw new GeolocationNotFoundException();
    }
}

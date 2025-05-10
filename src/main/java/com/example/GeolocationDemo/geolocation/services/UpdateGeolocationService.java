package com.example.GeolocationDemo.geolocation.services;

import com.example.GeolocationDemo.Command;
import com.example.GeolocationDemo.exception.GeolocationNotFoundException;
import com.example.GeolocationDemo.geolocation.GeolocationRepository;
import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import com.example.GeolocationDemo.geolocation.model.UpdateGeolocationCommand;
import com.example.GeolocationDemo.geolocation.validators.GeolocationValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateGeolocationService implements Command<UpdateGeolocationCommand, GeolocationDTO> {

    private final GeolocationRepository geolocationRepository;

    public UpdateGeolocationService(GeolocationRepository geolocationRepository) {
        this.geolocationRepository = geolocationRepository;
    }

    @Override
    public ResponseEntity<GeolocationDTO> execute(UpdateGeolocationCommand geolocationCommand) {
        Optional<Geolocation> optionalGeolocation = geolocationRepository.findById(geolocationCommand.getId());
        if (optionalGeolocation.isPresent()) {
            Geolocation geolocation = geolocationCommand.getGeolocation();
            geolocation.setId(geolocationCommand.getId());
            GeolocationValidator.execute(geolocation);
            geolocationRepository.save(geolocation);
            return ResponseEntity.ok(new GeolocationDTO(geolocation));
        }
        throw new GeolocationNotFoundException();
    }


}

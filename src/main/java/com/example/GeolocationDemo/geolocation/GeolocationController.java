package com.example.GeolocationDemo.geolocation;

import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import com.example.GeolocationDemo.geolocation.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class GeolocationController {

    private final GeolocationServiceImpl geolocationService;

    public GeolocationController(GeolocationServiceImpl geolocationService){
        this.geolocationService = geolocationService;
    }

    @PostMapping("/geolocation")
    public ResponseEntity<GeolocationDTO> createGeolocation(@RequestBody Geolocation geolocation) {
        return geolocationService.createGeolocation(geolocation);
    }

    @GetMapping("/geolocations")
    public ResponseEntity<List<GeolocationDTO>> getGeolocations() {
        return geolocationService.getGeolocations();
    }

    @GetMapping("/geolocation/{id}")
    public ResponseEntity<GeolocationDTO> getGeolocationById(@PathVariable Integer id) {
        return geolocationService.getGeolocation(id);
    }

    @GetMapping("/geolocation/user")
    public ResponseEntity<Geolocation> getGeolocationByUserId(@RequestParam Integer userId) {
        return geolocationService.getGeolocationByUserId(userId);
    }

    @PutMapping("/geolocation/{id}")
    public ResponseEntity<String> updateGeolocation(@PathVariable Integer id, @RequestBody Geolocation geolocation) {
        return geolocationService.updateGeolocation(id, geolocation);
    }

    @DeleteMapping("/geolocation/{id}")
    public ResponseEntity<Void> deleteGeolocation(@PathVariable Integer id) {
        return geolocationService.deleteGeolocation(id);
    }
}

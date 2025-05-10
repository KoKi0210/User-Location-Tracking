package com.example.GeolocationDemo.geolocation;

import com.example.GeolocationDemo.geolocation.model.Geolocation;
import com.example.GeolocationDemo.geolocation.model.GeolocationDTO;
import com.example.GeolocationDemo.geolocation.model.UpdateGeolocationCommand;
import com.example.GeolocationDemo.geolocation.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class GeolocationController {

    private final CreateGeolocationService createGeolocationService;
    private final DeleteGeolocationService deleteGeolocationService;
    private final GetGeolocationService getGeolocationService;
    private final GetGeolocationsService getGeolocationsService;
    private final UpdateGeolocationService updateGeolocationService;
    private final GetGeolocationByUserIdService getGeolocationByUserIdService;

    public GeolocationController(CreateGeolocationService createGeolocationService,
                                 DeleteGeolocationService deleteGeolocationService,
                                 GetGeolocationService getGeolocationService,
                                 GetGeolocationsService getGeolocationsService,
                                 UpdateGeolocationService updateGeolocationService,
                                 GetGeolocationByUserIdService getGeolocationByUserIdService) {
        this.createGeolocationService = createGeolocationService;
        this.deleteGeolocationService = deleteGeolocationService;
        this.getGeolocationService = getGeolocationService;
        this.getGeolocationsService = getGeolocationsService;
        this.updateGeolocationService = updateGeolocationService;
        this.getGeolocationByUserIdService = getGeolocationByUserIdService;
    }

    @PostMapping("/geolocation")
    public ResponseEntity<GeolocationDTO> createGeolocation(@RequestBody Geolocation geolocation) {
        return createGeolocationService.execute(geolocation);
    }

    @GetMapping("/geolocations")
    public ResponseEntity<List<GeolocationDTO>> getGeolocations() {
        return getGeolocationsService.execute(null);
    }

    @GetMapping("/geolocation/{id}")
    public ResponseEntity<GeolocationDTO> getGeolocationById(@PathVariable Integer id) {
        return getGeolocationService.execute(id);
    }

    @GetMapping("/geolocation/user")
    public ResponseEntity<Geolocation> getGeolocationByUserId(@RequestParam Integer userId) {
        return getGeolocationByUserIdService.execute(userId);
    }

    @PutMapping("/geolocation/{id}")
    public ResponseEntity<GeolocationDTO> updateGeolocation(@PathVariable Integer id, @RequestBody Geolocation geolocation) {
        return updateGeolocationService.execute(new UpdateGeolocationCommand(id, geolocation));
    }

    @DeleteMapping("/geolocation/{id}")
    public ResponseEntity<Void> deleteGeolocation(@PathVariable Integer id) {
        return deleteGeolocationService.execute(id);
    }
}

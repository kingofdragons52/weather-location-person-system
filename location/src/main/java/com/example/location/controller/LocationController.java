package com.example.location.controller;

import com.example.location.model.Location;
import com.example.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @GetMapping
    public List<Location> getAllLocations() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<Location> getLocationByCity(@PathVariable String city) {
        Location location = repository.findByCity(city);
        if (location != null) {
            return ResponseEntity.ok(location);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return repository.save(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable Long id,
            @RequestBody Location locationDetails) {

        Optional<Location> locationOptional = repository.findById(id);

        if (locationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Location location = locationOptional.get();
        location.setCity(locationDetails.getCity());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());

        Location updatedLocation = repository.save(location);
        return ResponseEntity.ok(updatedLocation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Location> partialUpdateLocation(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Optional<Location> locationOptional = repository.findById(id);

        if (locationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Location location = locationOptional.get();

        updates.forEach((key, value) -> {
            switch (key) {
                case "city":
                    location.setCity((String) value);
                    break;
                case "latitude":
                    location.setLatitude((Double) value);
                    break;
                case "longitude":
                    location.setLongitude((Double) value);
                    break;
            }
        });

        Location updatedLocation = repository.save(location);
        return ResponseEntity.ok(updatedLocation);
    }
}
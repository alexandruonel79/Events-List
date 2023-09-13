package com.example.event.controller;

import com.example.event.entity.Location;
import com.example.event.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/existsLocation")
    public void checkLocationExists(@RequestBody String locationName) {
        if (locationService.checkLocationExists(locationName)) {
            System.out.println("Location exists");
        } else {
            System.out.println("Location does not exist");
        }
    }
    @PostMapping("/addLocation")
    public void addLocation(@RequestBody Location location) {
        locationService.addLocation(location);
    }

    @GetMapping("/getLocations")
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    @PutMapping("/updateLocation/{id}")
    public void updateLocation(@RequestBody Location location, @PathVariable Long id) {

        if(locationService.getLocation(id) == null) {
            System.out.println("Location does not exist");
            return;
        }

        locationService.updateLocation(location);
    }
}

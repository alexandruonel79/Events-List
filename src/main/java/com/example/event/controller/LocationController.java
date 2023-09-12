package com.example.event.controller;

import com.example.event.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

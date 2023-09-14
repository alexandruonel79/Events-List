package com.example.event.controller;

import com.example.event.entity.Location;
import com.example.event.error.EventLocationException;
import com.example.event.error.LocationDoesNotExistException;
import com.example.event.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/getLocation/{id}")
    public ModelAndView getLocation(@PathVariable Long id) throws LocationDoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("location-details");
        modelAndView.addObject("location", locationService.getLocation(id));

        return modelAndView;
    }

    @GetMapping("/getLocations")
    public ModelAndView getLocations() {
        ModelAndView modelAndView = new ModelAndView("list-locations");
        modelAndView.addObject("locations", locationService.getAllLocations());

        return modelAndView;
    }

    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id) throws LocationDoesNotExistException {
        locationService.deleteLocation(id);
    }

    @PutMapping("/updateLocation/{id}")
    public void updateLocation(@RequestBody Location location, @PathVariable Long id)
            throws LocationDoesNotExistException {

        locationService.updateLocation(location,id);
    }
}

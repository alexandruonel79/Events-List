package com.example.event.service;

import com.example.event.entity.Location;
import com.example.event.error.EventLocationException;
import com.example.event.error.LocationDoesNotExistException;

import java.util.List;

public interface LocationService {
    public Location addLocation(Location location);
    public void deleteLocation(Long id) throws LocationDoesNotExistException;
    public Location updateLocation(Location location, Long id) throws LocationDoesNotExistException;
    public Location getLocation(Long id) throws LocationDoesNotExistException;
    public List<Location> getAllLocations();

    Boolean checkLocationExists(String locationName);
}

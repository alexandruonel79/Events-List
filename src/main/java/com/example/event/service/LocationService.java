package com.example.event.service;

import com.example.event.entity.Location;

import java.util.List;

public interface LocationService {
    public Location addLocation(Location location);
    public void deleteLocation(Long id);
    public Location updateLocation(Location location);
    public Location getLocation(Long id);
    public List<Location> getAllLocations();
}

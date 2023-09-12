package com.example.event.service;

import com.example.event.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    public Organizer addOrganizer(Organizer organizer);
    public void deleteOrganizer(Long id);
    public Organizer updateOrganizer(Organizer organizer);
    public Organizer getOrganizer(Long id);
    public List<Organizer> getAllOrganizers();
}

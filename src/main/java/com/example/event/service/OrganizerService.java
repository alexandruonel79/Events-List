package com.example.event.service;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;

import java.util.List;

public interface OrganizerService {
    public Organizer addOrganizer(Organizer organizer);
    public void deleteOrganizer(Long id) throws OrganizerDoesNotExistException;
    public Organizer updateOrganizer(Organizer organizer, Long id) throws OrganizerDoesNotExistException;
    public Organizer getOrganizer(Long id) throws EventOrganizerException, OrganizerDoesNotExistException;
    public List<Organizer> getAllOrganizers();
}

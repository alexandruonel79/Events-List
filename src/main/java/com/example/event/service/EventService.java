package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;

import java.util.List;

public interface EventService {
    public Event addEvent(Event event, Long locationId, Long organizerId);
    public void deleteEvent(Long id);
    public Event updateEventLocation(Event event, Long locationId);
    public Event updateEventOrganizer(Event event, Long organizerId);
    public Event getEvent(Long id);
    public List<Event> getAllEvents();
    public void addParticipant(Long idEvent, Participant participant);
    public void deleteEventParticipant(Long idEvent, Long idParticipant);
    public void updateEventParticipant(Long idEvent, Long id);
}

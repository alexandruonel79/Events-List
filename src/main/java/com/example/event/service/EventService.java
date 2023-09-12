package com.example.event.service;

import com.example.event.entity.Event;

import java.util.List;

public interface EventService {
    public Event addEvent(Event event, Long locationId, Long organizerId);
    public void deleteEvent(Long id);
    public Event updateEventLocation(Event event, Long locationId);
    public Event updateEventOrganizer(Event event, Long organizerId);
    public Event updateEventParticipants(Event event, List<Long> participantIds);
    public Event getEvent(Long id);
    public List<Event> getAllEvents();
    public void addParticipant(Long idEvent, Long idParticipant);

    void deleteParticipant(Long idEvent, Long idParticipant);

    void updateParticipant(Long idEvent, Long id);
}

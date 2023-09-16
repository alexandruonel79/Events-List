package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;
import com.example.event.entity.Review;
import com.example.event.error.*;

import java.util.List;

public interface EventService {
    public Event addEvent(Event event, Long locationId, Long organizerId) throws EventLocationException, EventOrganizerException, LocationDoesNotExistException, OrganizerDoesNotExistException;
    public void deleteEvent(Long id) throws EventDoesNotExistException;
    public Event updateEventLocation(Event event, Long locationId) throws EventLocationException, LocationDoesNotExistException;
    public Event updateEventOrganizer(Event event, Long organizerId) throws EventOrganizerException, OrganizerDoesNotExistException;
    public Event getEvent(Long id) throws EventDoesNotExistException;
    public List<Event> getAllEvents();
    public void addParticipant(Long idEvent, Participant participant) throws EventDoesNotExistException;
    public void deleteEventParticipant(Long idEvent, Long idParticipant) throws EventDoesNotExistException, ParticipantDoesNotExistException;
    public void updateEventParticipant(Long idEvent, Long id, Participant participant) throws ParticipantDoesNotExistException, EventDoesNotExistException;
    public void addReview(Long idEvent, Long idParticipant, Review review) throws EventDoesNotExistException;
    public void deleteEventReview(Long idEvent, Long idReview) throws EventDoesNotExistException, ReviewDoesNotExistException;
    public void updateEventReview(Long idEvent, Long idReview, Review review) throws ReviewDoesNotExistException, EventDoesNotExistException;
    public List<Review> getAllReviews(Long idEvent);

    List<Participant> getAllParticipants(Long idEvent) throws EventDoesNotExistException;
}

package com.example.event.service;

import com.example.event.entity.*;
import com.example.event.repository.EventRepository;
import com.example.event.repository.ParticipantRepository;
import com.example.event.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    private final ReviewRepository reviewRepository;
    private  LocationService locationService;
    private  OrganizerService organizerService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ParticipantRepository participantRepository, ReviewRepository reviewRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.reviewRepository = reviewRepository;
    }

    /// inject dependencies
    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    @Autowired
    public void setOrganizerService(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @Override
    public Event addEvent(Event event, Long locationId, Long organizerId) {
        System.out.println("EventServiceImpl: addEvent: locationId= " + locationId + ", organizerId= " + organizerId);
        Location location= locationService.getLocation(locationId);
        Organizer organizer= organizerService.getOrganizer(organizerId);

        event.setLocation(location);
        event.setOrganizer(organizer);

        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event updateEventLocation(Event event, Long locationId){
        Location newLocation= locationService.getLocation(locationId);
        event.setLocation(newLocation);

        return eventRepository.save(event);
    }

    @Override
    public Event updateEventOrganizer(Event event, Long organizerId) {

        Organizer newOrganizer= organizerService.getOrganizer(organizerId);
        event.setOrganizer(newOrganizer);

        return eventRepository.save(event);
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void addParticipant(Long idEvent, Participant participant) {

        Event event= eventRepository.findById(idEvent).get();

        event.addParticipant(participant);

        participantRepository.save(participant);
        eventRepository.save(event);
    }

    @Override
    public void deleteEventParticipant(Long idEvent, Long idParticipant) {
        Event event= eventRepository.findById(idEvent).get();
        Participant participant= participantRepository.findById(idParticipant).get();

        event.removeParticipant(participant);

        participantRepository.deleteById(idParticipant);
        eventRepository.save(event);
    }

    @Override
    public void updateEventParticipant(Long idEvent, Long id, Participant participant) {
        Event event= eventRepository.findById(idEvent).get();

        event.updateParticipant(participant);

        participantRepository.save(participant);
        eventRepository.save(event);
    }

    @Override
    public void addReview(Long idEvent, Long idParticipant, Review review) {
        eventRepository.findById(idEvent).get().addReview(review);
        reviewRepository.save(review);
    }

    @Override
    public void deleteEventReview(Long idEvent, Long idReview) {
        eventRepository.findById(idEvent).get().removeReview(reviewRepository.findById(idReview).get());
        reviewRepository.deleteById(idReview);
    }

    @Override
    public void updateEventReview(Long idEvent, Long idReview, Review review) {
        Event event= eventRepository.findById(idEvent).get();
        Review oldReview= reviewRepository.findById(idReview).get();

        event.removeReview(oldReview);
        event.addReview(review);

        reviewRepository.save(review);
        eventRepository.save(event);
    }

    @Override
    public List<Review> getAllReviews(Long idEvent) {
        return eventRepository.findById(idEvent).get().getReviews();
    }
}

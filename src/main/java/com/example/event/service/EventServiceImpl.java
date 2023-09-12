package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Location;
import com.example.event.entity.Organizer;
import com.example.event.entity.Participant;
import com.example.event.repository.EventRepository;
import com.example.event.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private  LocationService locationService;
    private  OrganizerService organizerService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ParticipantRepository participantRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
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
    public void updateEventParticipant(Long idEvent, Long id) {
        Event event= eventRepository.findById(idEvent).get();
        Participant participant= participantRepository.findById(id).get();

        event.updateParticipant(participant);

        participantRepository.save(participant);
        eventRepository.save(event);
    }
}

package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Location;
import com.example.event.entity.Organizer;
import com.example.event.entity.Participant;
import com.example.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final LocationService locationService;
    private final OrganizerService organizerService;
    private final ParticipantService participantService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,LocationService locationService,
                            OrganizerService organizerService, ParticipantService participantService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
        this.organizerService = organizerService;
        this.participantService = participantService;
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
    public Event updateEventParticipants(Event event, List<Long> participantIds) {
        return null;
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
    public void addParticipant(Long idEvent, Long idParticipant) {

        Event event= eventRepository.findById(idEvent).get();
        Participant participant= participantService.getParticipant(idParticipant,idEvent);

        event.addParticipant(participant);

        eventRepository.save(event);
    }

    @Override
    public void deleteParticipant(Long idEvent, Long idParticipant) {
        Event event= eventRepository.findById(idEvent).get();
        Participant participant= participantService.getParticipant(idParticipant,idEvent);

        event.removeParticipant(participant);

        eventRepository.save(event);
    }

    @Override
    public void updateParticipant(Long idEvent, Long id) {
        Event event= eventRepository.findById(idEvent).get();
        Participant participant= participantService.getParticipant(id,idEvent);

        event.updateParticipant(participant);

        eventRepository.save(event);
    }
}

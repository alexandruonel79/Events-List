package com.example.event.controller;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;
import com.example.event.entity.Review;
import com.example.event.error.*;
import com.example.event.request.EventRequest;
import com.example.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody EventRequest eventRequest) throws EventLocationException,
            EventOrganizerException, LocationDoesNotExistException {

        Event event= Event.builder().
                eventName(eventRequest.getEventName()).
                description(eventRequest.getDescription()).
                reviews(null).
                participants(null).
                organizer(null).
                build();

        eventService.addEvent(event, eventRequest.getLocationId(), eventRequest.getOrganizerId());
    }

    @GetMapping("/getAllEvents")
    public ModelAndView getAllEvents() {
      List<Event>  eventList=eventService.getAllEvents();
      /// use thymeleaf to display the list
        ModelAndView modelAndView = new ModelAndView("list-events");
        modelAndView.addObject("events", eventList);

        return modelAndView;
    }

    @GetMapping("/getEvent/{id}")
    public Event getEvent(@PathVariable Long id) throws EventDoesNotExistException {
       return eventService.getEvent(id);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id) throws EventDoesNotExistException {
        eventService.deleteEvent(id);
    }

    @PutMapping("/updateEvent/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Long locationId, @RequestBody Long organizerId)
            throws EventLocationException, EventOrganizerException, EventDoesNotExistException,
            LocationDoesNotExistException {
        Event event= eventService.getEvent(id);
        eventService.updateEventLocation(event, locationId);
        eventService.updateEventOrganizer(event, organizerId);
    }

    /// Participant

    @PostMapping("/addParticipant/{idEvent}")
    public void addParticipant(@PathVariable Long idEvent, @RequestBody Participant participant)
            throws EventDoesNotExistException {
        eventService.addParticipant(idEvent, participant);
    }

    @DeleteMapping("/deleteParticipant/{idEvent}/{idParticipant}")
    public void deleteParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        eventService.deleteEventParticipant(idEvent, idParticipant);
    }

    @PutMapping("/updateParticipant/{idEvent}/{idParticipant}")
    public void updateParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                                  @RequestBody Participant participant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        eventService.updateEventParticipant(idEvent, idParticipant, participant);
    }

    @GetMapping("/getParticipant/{idEvent}/{idParticipant}")
    public Participant getParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        return eventService.getEvent(idEvent).getParticipant(idParticipant);
    }

    @GetMapping("/getAllParticipants/{idEvent}")
    public List<Participant> getAllParticipants(@PathVariable Long idEvent)
            throws EventDoesNotExistException {
        return eventService.getAllParticipants(idEvent);
    }

    /// Review

    @PostMapping("/addReview/{idEvent}/{idParticipant}")
    public void addReview(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                          @RequestBody Review review) throws EventDoesNotExistException {
        eventService.addReview(idEvent, idParticipant, review);
    }

    @DeleteMapping("/deleteReview/{idEvent}/{idReview}")
    public void deleteReview(@PathVariable Long idEvent, @PathVariable Long idReview)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        eventService.deleteEventReview(idEvent, idReview);
    }

    @PutMapping("/updateReview/{idEvent}/{idReview}")
    public void updateReview(@PathVariable Long idEvent, @PathVariable Long idReview,
                             @RequestBody Review review)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        eventService.updateEventReview(idEvent, idReview, review);
    }

    @GetMapping("/getReview/{idEvent}/{idReview}")
    public Review getReview(@PathVariable Long idEvent, @PathVariable Long idReview)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        return eventService.getEvent(idEvent).getReview(idReview);
    }
}

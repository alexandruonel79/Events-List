package com.example.event.controller;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;
import com.example.event.entity.Review;
import com.example.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestBody String eventName, @RequestBody String description, @RequestBody Long locationId,
                         @RequestBody Long organizerId) {

        Event event= Event.builder().
                eventName(eventName).
                description(description).
                reviews(null).
                participants(null).
                organizer(null).
                build();

        eventService.addEvent(event, locationId, organizerId);
    }

    @GetMapping("/getAllEvents")
    public void getAllEvents() {
        eventService.getAllEvents();
    }

    @GetMapping("/getEvent/{id}")
    public Event getEvent(@PathVariable Long id) {
       return eventService.getEvent(id);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping("/updateEvent/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Long locationId, @RequestBody Long organizerId) {
        Event event= eventService.getEvent(id);
        eventService.updateEventLocation(event, locationId);
        eventService.updateEventOrganizer(event, organizerId);
    }

    /// Participant

    @PostMapping("/addParticipant/{idEvent}")
    public void addParticipant(@PathVariable Long idEvent, @RequestBody Participant participant) {
        eventService.addParticipant(idEvent, participant);
    }

    @DeleteMapping("/deleteParticipant/{idEvent}/{idParticipant}")
    public void deleteParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant) {
        eventService.deleteEventParticipant(idEvent, idParticipant);
    }

    @PutMapping("/updateParticipant/{idEvent}/{idParticipant}")
    public void updateParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                                  @RequestBody Participant participant) {
        eventService.updateEventParticipant(idEvent, idParticipant, participant);
    }

    @GetMapping("/getParticipant/{idEvent}/{idParticipant}")
    public Participant getParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant) {
        return eventService.getEvent(idEvent).getParticipant(idParticipant);
    }

    /// Review

    @PostMapping("/addReview/{idEvent}/{idParticipant}")
    public void addReview(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                          @RequestBody Review review) {
        eventService.addReview(idEvent, idParticipant, review);
    }

    @DeleteMapping("/deleteReview/{idEvent}/{idReview}")
    public void deleteReview(@PathVariable Long idEvent, @PathVariable Long idReview) {
        eventService.deleteEventReview(idEvent, idReview);
    }

    @PutMapping("/updateReview/{idEvent}/{idReview}")
    public void updateReview(@PathVariable Long idEvent, @PathVariable Long idReview,
                             @RequestBody Review review) {
        eventService.updateEventReview(idEvent, idReview, review);
    }

    @GetMapping("/getReview/{idEvent}/{idReview}")
    public Review getReview(@PathVariable Long idEvent, @PathVariable Long idReview) {
        return eventService.getEvent(idEvent).getReview(idReview);
    }
}

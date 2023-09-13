package com.example.event.controller;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;
import com.example.event.service.OrganizerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping("/addOrganizer")
    public void addOrganizer(@RequestBody Organizer organizer) {
        organizerService.addOrganizer(organizer);
    }

    @DeleteMapping("/deleteOrganizer")
    public void deleteOrganizer(@RequestBody Long id) throws OrganizerDoesNotExistException {
        organizerService.deleteOrganizer(id);
    }

    @PutMapping("/updateOrganizer/{id}")
    public void updateOrganizer(@RequestBody Organizer organizer, @PathVariable Long id)
            throws EventOrganizerException, OrganizerDoesNotExistException {
        if(organizerService.getOrganizer(id) == null) {
            System.out.println("Organizer with id " + id + " not found.");
            return;
        }
        organizerService.updateOrganizer(organizer, id);
    }

    @GetMapping("/getOrganizer/{id}")
    public Organizer getOrganizer(@PathVariable Long id) throws EventOrganizerException {
        return organizerService.getOrganizer(id);
    }

    @GetMapping("/getAllOrganizers")
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

}

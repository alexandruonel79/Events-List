package com.example.event.controller;

import com.example.event.entity.Organizer;
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
    public void deleteOrganizer(@RequestBody Long id) {
        organizerService.deleteOrganizer(id);
    }

    @PutMapping("/updateOrganizer/{id}")
    public void updateOrganizer(@RequestBody Organizer organizer, @PathVariable Long id) {
        if(organizerService.getOrganizer(id) == null) {
            System.out.println("Organizer with id " + id + " not found.");
            return;
        }
        organizerService.updateOrganizer(organizer);
    }

    @GetMapping("/getOrganizer/{id}")
    public Organizer getOrganizer(@PathVariable Long id) {
        return organizerService.getOrganizer(id);
    }

    @GetMapping("/getAllOrganizers")
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

}

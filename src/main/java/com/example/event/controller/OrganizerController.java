package com.example.event.controller;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;
import com.example.event.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping("/addOrganizer")
    public ModelAndView addOrganizer( @RequestParam String name,
                                      @RequestParam String email,
                                      @RequestParam String phone) {

    Organizer organizer = Organizer.builder().
            name(name).
            email(email).
            phone(phone).
            build();

        organizerService.addOrganizer(organizer);

        ModelAndView modelAndView = new ModelAndView("add-organizer");
        modelAndView.addObject("organizer", organizer);

        return modelAndView;
    }
    @GetMapping("/addOrganizer")
    public ModelAndView showAddOrganizer() {
        ModelAndView modelAndView = new ModelAndView("add-organizer");

        return modelAndView;
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
    public ModelAndView getOrganizer(@PathVariable Long id) throws EventOrganizerException {
        ModelAndView modelAndView = new ModelAndView("organizer-details");
        modelAndView.addObject("organizer", organizerService.getOrganizer(id));

        return modelAndView;
    }

    @GetMapping("/getAllOrganizers")
    public ModelAndView getAllOrganizers() {
        ModelAndView modelAndView = new ModelAndView("list-organizers");
        modelAndView.addObject("organizers", organizerService.getAllOrganizers());

        return modelAndView;
    }

}

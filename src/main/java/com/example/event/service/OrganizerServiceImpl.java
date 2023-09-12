package com.example.event.service;

import com.example.event.entity.Organizer;
import com.example.event.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService{
    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public Organizer addOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }

    @Override
    public Organizer updateOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerRepository.findById(id).get();
    }

    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }
}

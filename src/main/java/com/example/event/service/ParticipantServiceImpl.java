package com.example.event.service;

import com.example.event.entity.Participant;
import com.example.event.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    private final ParticipantRepository participantRepository;
    private final EventService eventService;

    @Autowired
    public ParticipantServiceImpl(ParticipantRepository participantRepository, EventService eventService) {
        this.participantRepository = participantRepository;
        this.eventService = eventService;
    }

    @Override
    public void addParticipant(Participant participant, Long eventId) {
        participantRepository.save(participant);

        eventService.addParticipant(eventId, participant.getId());
    }

    @Override
    public void deleteParticipant(Long idParticipant, Long idEvent) {
        participantRepository.deleteById(idParticipant);

        eventService.deleteParticipant(idEvent, idParticipant);
    }

    @Override
    public void updateParticipant(Participant participant, Long eventId) {
        participantRepository.save(participant);

        eventService.updateParticipant(eventId, participant.getId());
    }

    @Override
    public Participant getParticipant(Long idParticipant, Long idEvent) {
        return participantRepository.findById(idParticipant).get();
    }

    @Override
    public List<Participant> getAllParticipants(Long idEvent) {
        return participantRepository.findAll();
    }
}

package com.example.event.service;

import com.example.event.entity.Participant;

import java.util.List;

public interface ParticipantService {
    public void addParticipant(Participant participant, Long eventId);
    public void deleteParticipant(Long idParticipant, Long idEvent);
    public void updateParticipant(Participant participant, Long eventId);
    public Participant getParticipant(Long idParticipant, Long idEvent);
    public List<Participant> getAllParticipants(Long idEvent);
}

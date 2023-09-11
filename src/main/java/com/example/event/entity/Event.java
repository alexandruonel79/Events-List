package com.example.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Event {

    @SequenceGenerator(
            name = "event_seq",
            sequenceName = "event_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            generator = "event_seq"
    )

    @Id
    private Long id;

    private String eventName;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id") // specificați coloana cheii străine în tabelul evenimentului
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id") // specificați coloana cheii străine în tabelul evenimentului
    private Organizer organizer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_participant",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> participants;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Review> reviews;
}

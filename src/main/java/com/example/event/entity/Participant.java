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

public class Participant {
    @SequenceGenerator(
            name = "participant_seq",
            sequenceName = "participant_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "participant_seq"
    )

    @Id
    private Long id;

    private String name;
    private String email;
    private String phone;

    @ManyToMany(mappedBy = "participants",
                cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "author",
               cascade = CascadeType.ALL)
    private List<Review> reviews;
}

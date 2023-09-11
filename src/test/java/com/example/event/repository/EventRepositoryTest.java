package com.example.event.repository;

import com.example.event.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepositoryUnderTest;

    @Test
    public void addEvent(){
        Location location = Location.builder()
                .name("Test Location")
                .address("Test Address")
                .city("Test City")
                .country("Test Country")
                .postalCode("000000")
                .build();

        Organizer organizer = Organizer.builder()
                .name("Test Organizer")
                .email("test@gmail.com")
                .phone("0000000000")
                .build();
        Participant participant = Participant.builder()
                .name("Test Participant")
                .phone("0000000000")
                .email("aaa@gmaol.com")
                ///fix cu ideea lui dailyCodeBuffer
                .events(null)
                .build();

        Review review = Review.builder()
                .rating(5)
                .comment("Test Comment")
                .author(participant)
                .event(null)
                .build();

        Event event = Event.builder()
                .eventName("Test Event")
                .description("Test Description")
                .location(location)
                .participants(List.of())
                .organizer(organizer)
                .reviews(List.of(review))
                .build();

        review.setEvent(event);
        participant.setEvents(List.of(event));

        eventRepositoryUnderTest.save(event);
    }
}
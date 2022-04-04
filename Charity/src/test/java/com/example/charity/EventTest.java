package com.example.charity;

import com.example.charity.model.Event;
import com.example.charity.model.Organization;
import com.example.charity.repository.EventRepository;
import com.example.charity.repository.OrganizationRepository;
import com.example.charity.service.EventService;
import com.example.charity.service.OrganizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventTest {


    @InjectMocks
    private EventService eventService;


    @Mock
    private EventRepository eventRepository;



    @Test
    @DisplayName("Get event from db")
    void test_getEvent() {
        // Arrange
        Long id = 1L;
        Event event = new Event();
        event.setId(id);
        when(eventRepository.findById(id)).thenReturn(Optional.of(event));

        // Act
        Event actualEvent = eventService.getEventById(id);

        // Assert
        assertEquals(id, actualEvent.getId());
        verify(eventRepository, times(1)).findById(id);
    }



    @Test
    @DisplayName("Save event")
    void test_saveEvent() {
        // Arrange
        Event event = buildEvent();

        when(eventRepository.save(event)).thenReturn(event);

        // Act
        Event saved = eventService.saveEvent(event);

        // Assert
        verify(eventRepository).save(event);
        assertEquals(event, saved);
    }





    private Event buildEvent() {


        Event event = new Event();
        event.setId(1L);
        event.setName("Save the forests Event");
        event.setLocation("Bucharest");
        event.setDate(LocalDate.parse("2022-03-30"));

        return event;
    }
}
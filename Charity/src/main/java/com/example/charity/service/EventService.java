package com.example.charity.service;

import com.example.charity.exception.NotFoundException;
import com.example.charity.model.Donation;
import com.example.charity.model.Event;
import com.example.charity.model.SocialCause;
import com.example.charity.repository.EventRepository;
import com.example.charity.repository.SocialCouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    public final EventRepository eventRepository;
    public final SocialCauseService socialCouseService;

    @Autowired
    public EventService(EventRepository eventRepository, SocialCauseService socialCouseService) {
        this.eventRepository = eventRepository;
        this.socialCouseService = socialCouseService;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
//        System.out.println(socialCauseId);
//        SocialCause socialCause = socialCouseService.getSocialCauseById(socialCauseId);
//        System.out.println(socialCause);
//        event.setSocialCause(socialCause);

        return eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.orElseThrow(() -> new NotFoundException("Event  not found!", "event.not.found"));

    }


    public void deleteEvent(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if(optionalEvent.isPresent()){
            eventRepository.delete(optionalEvent.get());
        }
        else {
            throw new NotFoundException("Event not found", "event.not.found");
        }
    }
}

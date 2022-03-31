package com.example.charity.service;

import com.example.charity.exception.NotFoundException;
import com.example.charity.model.Event;
import com.example.charity.model.SocialCause;
import com.example.charity.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    public final EventRepository eventRepository;
    public final SocialCauseService socialCauseService;

    @Autowired
    public EventService(EventRepository eventRepository, SocialCauseService socialCauseService) {
        this.eventRepository = eventRepository;
        this.socialCauseService = socialCauseService;
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

    public Event assignSocialCauseToEvent(Long eventId, Long socialCauseId) {
        Event event = getEventById(eventId);
        SocialCause socialCause = socialCauseService.getSocialCauseById(socialCauseId);
        event.setSocialCause(socialCause);
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventUpdated) {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            eventUpdated.setId(id);
            eventUpdated.setName(eventUpdated.getName() == null ? eventOptional.get().getName() : eventUpdated.getName());
            eventUpdated.setDate(eventUpdated.getDate() == null ? eventOptional.get().getDate() : eventUpdated.getDate());
            eventUpdated.setLocation(eventUpdated.getLocation() == null ? eventOptional.get().getLocation() : eventUpdated.getLocation());

            return eventRepository.save(eventUpdated);
        } else {
            throw new NotFoundException("Event not found!", "event.not.found");
        }
    }

}

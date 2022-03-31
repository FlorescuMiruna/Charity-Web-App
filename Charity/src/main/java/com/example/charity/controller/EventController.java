package com.example.charity.controller;



import com.example.charity.model.Event;
import com.example.charity.model.SocialCause;
import com.example.charity.service.EventService;
import com.example.charity.service.SocialCauseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {
    public final EventService eventService;
    public final SocialCauseService socialCauseService;
    private Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public EventController(EventService eventService, SocialCauseService socialCauseService) {
        this.eventService = eventService;
        this.socialCauseService = socialCauseService;
    }


    @GetMapping(value = "")
    public List<Event> getAllEvents(){

        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable("id") Long id){
        return eventService.getEventById(id);
    }

    @PostMapping(value = "")
    public Event saveEvent(@RequestBody Event event){
        eventService.saveEvent(event);
        return event;

    }



    @DeleteMapping(value = "/{id}")
    public  void deleteEvent(@PathVariable Long id){
        logger.info("Deleted event with {}", id);
        eventService.deleteEvent(id);

    }

    @PutMapping("/{eventId}/social-cause/{socialCauseId}")
    Event assignSocialCauseToEvent(@PathVariable Long eventId, @PathVariable Long socialCauseId
    ) {


        return eventService.assignSocialCauseToEvent(eventId,socialCauseId);

    }


}

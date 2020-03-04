package com.mins.corona.api.event;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDTO event) {

        Event dbParam = new Event();
        BeanUtils.copyProperties(event, dbParam);

        Event savedEvent = eventRepository.save(dbParam); // db save
        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(EventController.class).slash(savedEvent.getId());
        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(savedEvent);
    }
}

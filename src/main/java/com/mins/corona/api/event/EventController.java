package com.mins.corona.api.event;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventService eventService;

    private final EventRepository eventRepository;

    public EventController(EventService eventService, EventRepository eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDTO event, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Event dbParam = new Event();
        BeanUtils.copyProperties(event, dbParam);

        eventService.parameterValidationCheck(dbParam); // validation check

        Event savedEvent = eventRepository.save(dbParam); // db save

        return ResponseEntity
                .created(linkTo(EventController.class).slash(savedEvent.getId()).toUri())
                .body(new EventResource(savedEvent));
    }
}

package com.mins.corona.api.event;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends RepresentationModel {

    @JsonUnwrapped
    private Event event;

    public EventResource(Event event) {
        this.event = event;

        // hateoas 설정
        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(EventController.class);
        add(webMvcLinkBuilder.slash(event.getId()).withSelfRel());
        add(webMvcLinkBuilder.slash(event.getId()).withRel("update-event"));
        add(webMvcLinkBuilder.withRel("query-events"));
    }
}

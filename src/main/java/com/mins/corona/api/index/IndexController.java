package com.mins.corona.api.index;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
public class IndexController {

    @GetMapping("/api")
    public ResponseEntity index() {
        var str = linkTo(IndexController.class);

        RepresentationModel representationModel = new RepresentationModel();
        representationModel.add(str.withRel("event"));

        return ResponseEntity.ok(representationModel);
    }
}

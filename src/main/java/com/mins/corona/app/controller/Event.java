package com.mins.corona.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corona/event")
public class Event {

    @RequestMapping("")
    public String list() {

        return "event";
    }
}

package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Event;
import com.tschlatd.springdatajpa.service.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    @Autowired
    private EventServiceImpl service;

    @GetMapping("")
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return service.getOneEvent(id);
    }

    @PostMapping("")
    public Event createEvent(@RequestBody Event Event) {
        return service.createEvent(Event);
    }

    @PutMapping("")
    public Event updateEvent(@RequestBody Event Event) {
        return service.createEvent(Event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        service.deleteEvent(id);
    }
}
package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Event;
import com.scarc.springbootjparest.services.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Events")
public class EventController {
    @Autowired
    private EventServiceImpl svc;

    @GetMapping("/")
    public List<Event> getAllEvents() {
        return svc.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id) {
        return svc.getOneEvent(id);
    }

    @PostMapping("/")
    public Event createEvent(@RequestBody Event Event) {
        return svc.createEvent(Event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@RequestBody Event Event) {
        return svc.createEvent(Event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id) {
        svc.deleteEvent(id);
    }
}
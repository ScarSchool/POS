package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getOneEvent(int id);
    Event createEvent(Event Event);
    boolean deleteEvent(int id);
}

package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EventService {
    List<Event> getAllEvents();
    Event getOneEvent(int id);
    Event createEvent(Event Event);
    boolean deleteEvent(int id);
}

package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Event;
import com.tschlatd.springdatajpa.repository.EventRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    @Getter
    @Setter
    private EventRepository repo;

    @Override
    public List<Event> getAllEvents() {
        return repo.findAll();
    }

    @Override
    public Event getOneEvent(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Event createEvent(Event Event) {
        return repo.save(Event);
    }

    @Override
    public boolean deleteEvent(int id) {
        repo.deleteById(id);
        return true;
    }
}
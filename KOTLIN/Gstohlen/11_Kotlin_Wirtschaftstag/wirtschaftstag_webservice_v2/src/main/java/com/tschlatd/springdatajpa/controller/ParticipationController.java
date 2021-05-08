package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Participation;
import com.tschlatd.springdatajpa.service.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/participations")
public class ParticipationController {
    @Autowired
    private ParticipationServiceImpl service;

    @GetMapping("")
    public List<Participation> getAllParticipations() {
        return service.getAllParticipations();
    }

    @GetMapping("/{id}")
    public Participation getParticipationById(@PathVariable int id) {
        return service.getOneParticipation(id);
    }

    @PostMapping("")
    public Participation createParticipation(@RequestBody Participation Participation) {
        return service.createParticipation(Participation);
    }

    @PutMapping("")
    public Participation updateParticipation(@RequestBody Participation Participation) {
        return service.createParticipation(Participation);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable int id) {
        service.deleteParticipation(id);
    }
}
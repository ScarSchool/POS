package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Participation;
import com.scarc.springbootjparest.services.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Participations")
public class ParticipationController {
    @Autowired
    private ParticipationServiceImpl svc;

    @GetMapping("/")
    public List<Participation> getAllParticipations() {
        return svc.getAllParticipations();
    }

    @GetMapping("/{id}")
    public Participation getParticipationById(@PathVariable int id) {
        return svc.getOneParticipation(id);
    }

    @PostMapping("/")
    public Participation createParticipation(@RequestBody Participation Participation) {
        return svc.createParticipation(Participation);
    }

    @PutMapping("/")
    public Participation updateParticipation(@RequestBody Participation Participation) {
        return svc.createParticipation(Participation);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable int id) {
        svc.deleteParticipation(id);
    }
}
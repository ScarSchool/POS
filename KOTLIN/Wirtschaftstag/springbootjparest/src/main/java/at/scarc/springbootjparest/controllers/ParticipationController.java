package at.scarc.springbootjparest.controllers;

import at.scarc.springbootjparest.models.Event;
import at.scarc.springbootjparest.models.Participation;
import at.scarc.springbootjparest.services.EventServiceImpl;
import at.scarc.springbootjparest.services.ParticipationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    @Autowired
    private ParticipationServiceImpl svc;

    @GetMapping()
    public List<Participation> getAllParticipations(@RequestParam long eventId) {
        if (eventId != -1) {
            return svc.getByEventId(eventId);
        }
        return svc.getAll();
    }

    @GetMapping("/{id}")
    public Participation getParticipationById(@PathVariable Long id) { return svc.getOne(id); }

    @PostMapping()
    public Participation createParticipation(@RequestBody Participation participation) { return svc.createOrUpdateOne(participation); }

    @PutMapping()
    public Participation updateParticipation(@RequestBody Participation participation) { return svc.createOrUpdateOne(participation); }

    @DeleteMapping("/{id}")
    public boolean deleteParticipation(@PathVariable Long id) { return svc.delete(id); }
}
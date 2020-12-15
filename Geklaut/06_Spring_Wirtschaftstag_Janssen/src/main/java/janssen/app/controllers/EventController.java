package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.EventDAO;
import janssen.app.models.Event;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

  private final EventDAO dao;

  public EventController(EventDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<Event> listEvents() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public Event getEvent(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public Event createEvent(@RequestBody Event event) {
    return dao.save(event);
  }

  @PutMapping({"", "{id}"})
  public Event updateEvent(@PathVariable Optional<String> id, @RequestBody Event event) {
    id.ifPresent(idVal -> event.setId( Integer.parseInt(idVal) ));
    return dao.save(event);
  }

  @DeleteMapping("{id}")
  public void deleteEvent(@PathVariable String id) {
    Event event = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(event);
  }

}

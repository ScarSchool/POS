package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.ParticipationDAO;
import janssen.app.models.Participation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

  private final ParticipationDAO dao;

  public ParticipationController(ParticipationDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<Participation> listParticipations() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public Participation getParticipation(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public Participation createParticipation(@RequestBody Participation participation) {
    return dao.save(participation);
  }

  @PutMapping({"", "{id}"})
  public Participation updateParticipation(@PathVariable Optional<String> id, @RequestBody Participation participation) {
    id.ifPresent(idVal -> participation.setId( Integer.parseInt(idVal) ));
    return dao.save(participation);
  }

  @DeleteMapping("{id}")
  public void deleteParticipation(@PathVariable String id) {
    Participation participation = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(participation);
  }

}

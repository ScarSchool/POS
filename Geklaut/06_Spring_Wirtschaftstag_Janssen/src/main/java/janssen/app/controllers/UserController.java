package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.UserDAO;
import janssen.app.models.User;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserDAO dao;

  public UserController(UserDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<User> listUsers() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public User getUser(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    System.out.println(user);
    return dao.save(user);
  }

  @PutMapping({"", "{id}"})
  public User updateUser(@PathVariable Optional<String> id, @RequestBody User user) {
    id.ifPresent(idVal -> user.setId( Integer.parseInt(idVal) ));
    return dao.save(user);
  }

  @DeleteMapping("{id}")
  public void deleteUser(@PathVariable String id) {
    User user = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(user);
  }

}

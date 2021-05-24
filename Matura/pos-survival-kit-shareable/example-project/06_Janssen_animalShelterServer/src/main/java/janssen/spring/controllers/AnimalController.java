package janssen.spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import janssen.spring.JsonViews;
import janssen.spring.Utils;
import janssen.spring.entities.Animal;
import janssen.spring.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/animals")
public class AnimalController {

  protected final AnimalService service;

  public AnimalController(@Autowired AnimalService service) {
    this.service = service;
  }

  @GetMapping
  @JsonView(JsonViews.Summary.class)
  public Collection<Animal> listAll() {
    return service.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Animal create(@Valid @RequestBody Animal animal, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      return service.create(animal);
    }
    catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }
  }

  @GetMapping("/{id}")
  public Animal get(@PathVariable long id) {
    try {
      return service.get(id);
    }
    catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }

  @PutMapping("/{id}")
  public void update(@PathVariable long id, @Valid @RequestBody Animal animal, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      service.update(id, animal);
    }
    catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
    catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    service.delete(id);
  }

}

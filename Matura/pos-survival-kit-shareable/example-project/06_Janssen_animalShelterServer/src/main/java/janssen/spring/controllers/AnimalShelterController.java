package janssen.spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import janssen.spring.JsonViews;
import janssen.spring.Utils;
import janssen.spring.entities.AnimalShelter;
import janssen.spring.services.AnimalShelterService;
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
@RequestMapping("/shelters")
public class AnimalShelterController {

  protected final AnimalShelterService service;

  public AnimalShelterController(@Autowired AnimalShelterService service) {
    this.service = service;
  }

  @GetMapping
  @JsonView(JsonViews.Summary.class)
  public Collection<AnimalShelter> listAll() {
    return service.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AnimalShelter create(@Valid @RequestBody AnimalShelter animalShelter, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      return service.create(animalShelter);
    }
    catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }
  }

  @GetMapping("/{address}")
  public AnimalShelter get(@PathVariable String address) {
    try {
      return service.get(address);
    }
    catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }

  @PutMapping("/{address}")
  public void update(@PathVariable String address, @Valid @RequestBody AnimalShelter animalShelter, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      service.update(address, animalShelter);
    }
    catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
    catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }
  }

  @DeleteMapping("/{address}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String address) {
    service.delete(address);
  }

}

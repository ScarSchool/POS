package janssen.spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import janssen.spring.JsonViews;
import janssen.spring.Utils;
import janssen.spring.entities.Sales;
import janssen.spring.services.SalesService;
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
@RequestMapping("/sales")
public class SalesController {

  protected final SalesService service;

  public SalesController(@Autowired SalesService service) {
    this.service = service;
  }

  @GetMapping
  @JsonView(JsonViews.Summary.class)
  public Collection<Sales> listAll() {
    return service.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Sales create(@Valid @RequestBody Sales sales, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      return service.create(sales);
    }
    catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }
  }

  @GetMapping("/{id}")
  public Sales get(@PathVariable long id) {
    try {
      return service.get(id);
    }
    catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }

  @PutMapping("/{id}")
  public void update(@PathVariable long id, @Valid @RequestBody Sales sales, BindingResult validationResult) {
    try {
      Utils.exceptionFromValidationResult(validationResult);
      service.update(id, sales);
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

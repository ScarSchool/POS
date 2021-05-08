package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Responsible;
import com.tschlatd.springdatajpa.repository.ResponsibleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/responsibles")
public class ResponsibleController {

    @Autowired
    @Getter
    @Setter
    private ResponsibleRepository repo;

    @GetMapping
    public List<Responsible> listResponsibles() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Responsible getResponsible(@PathVariable String id) {
        return repo
                .findById( Integer.parseInt(id) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsible with id " + id + " not found!"));
    }

    @PostMapping
    public Responsible createResponsible(@RequestBody Responsible user) {
        System.out.println(user);
        return repo.save(user);
    }

    @PutMapping({"", "{id}"})
    public Responsible updateResponsible(@PathVariable Optional<String> id, @RequestBody Responsible user) {
        id.ifPresent(idVal -> user.setId( Integer.parseInt(idVal) ));
        return repo.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteResponsible(@PathVariable String id) {
        Optional<Responsible> user = repo.findById( Integer.parseInt(id) );
        user.ifPresent(repo::delete);
    }

}
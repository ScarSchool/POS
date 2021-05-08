package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Pupil;
import com.tschlatd.springdatajpa.repository.PupilRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pupils")
public class PupilController {

    @Autowired
    @Getter
    @Setter
    private PupilRepository repo;

    @GetMapping
    public List<Pupil> listPupils() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Pupil getPupil(@PathVariable String id) {
        return repo
                .findById( Integer.parseInt(id) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pupil with id " + id + " not found!"));
    }

    @PostMapping
    public Pupil createPupil(@RequestBody Pupil user) {
        System.out.println(user);
        return repo.save(user);
    }

    @PutMapping({"", "{id}"})
    public Pupil updatePupil(@PathVariable Optional<String> id, @RequestBody Pupil user) {
        id.ifPresent(idVal -> user.setId( Integer.parseInt(idVal) ));
        return repo.save(user);
    }

    @DeleteMapping("{id}")
    public void deletePupil(@PathVariable String id) {
        Optional<Pupil> user = repo.findById( Integer.parseInt(id) );
        user.ifPresent(repo::delete);
    }
}
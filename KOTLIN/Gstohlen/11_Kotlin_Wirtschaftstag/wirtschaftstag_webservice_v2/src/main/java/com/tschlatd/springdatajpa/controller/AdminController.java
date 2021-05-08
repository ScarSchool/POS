package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.repository.AdminRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admins")
public class AdminController {

    @Autowired
    @Getter
    @Setter
    private AdminRepository repo;

    @GetMapping
    public List<Admin> listAdmins() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    public Admin getAdmin(@PathVariable String id) {
        return repo
                .findById( Integer.parseInt(id) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin with id " + id + " not found!"));
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin user) {
        System.out.println(user);
        return repo.save(user);
    }

    @PutMapping({"", "{id}"})
    public Admin updateAdmin(@PathVariable Optional<String> id, @RequestBody Admin user) {
        id.ifPresent(idVal -> user.setId( Integer.parseInt(idVal) ));
        return repo.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteAdmin(@PathVariable String id) {
        Optional<Admin> user = repo.findById( Integer.parseInt(id) );
        user.ifPresent(repo::delete);
    }
}

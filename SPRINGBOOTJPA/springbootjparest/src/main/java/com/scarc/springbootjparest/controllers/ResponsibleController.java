package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Responsible;
import com.scarc.springbootjparest.services.ResponsibleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Responsibles")
public class ResponsibleController {
    @Autowired
    private ResponsibleServiceImpl svc;

    @GetMapping("/")
    public List<Responsible> getAllResponsibles() {
        return svc.getAllResponsibles();
    }

    @GetMapping("/{id}")
    public Responsible getResponsibleById(@PathVariable int id) {
        return svc.getOneResponsible(id);
    }

    @PostMapping("/")
    public Responsible createResponsible(@RequestBody Responsible Responsible) {
        return svc.createResponsible(Responsible);
    }

    @PutMapping("/{id}")
    public Responsible updateResponsible(@RequestBody Responsible Responsible) {
        return svc.createResponsible(Responsible);
    }

    @DeleteMapping("/{id}")
    public void deleteResponsible(@PathVariable int id) {
        svc.deleteResponsible(id);
    }
}
package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Pupil;
import com.scarc.springbootjparest.services.PupilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Pupils")
public class PupilController {
    @Autowired
    private PupilServiceImpl svc;

    @GetMapping("/")
    public List<Pupil> getAllPupils() {
        return svc.getAllPupils();
    }

    @GetMapping("/{id}")
    public Pupil getPupilById(@PathVariable int id) {
        return svc.getOnePupil(id);
    }

    @PostMapping("/")
    public Pupil createPupil(@RequestBody Pupil Pupil) {
        return svc.createPupil(Pupil);
    }

    @PutMapping("/{id}")
    public Pupil updatePupil(@RequestBody Pupil Pupil) {
        return svc.createPupil(Pupil);
    }

    @DeleteMapping("/{id}")
    public void deletePupil(@PathVariable int id) {
        svc.deletePupil(id);
    }
}
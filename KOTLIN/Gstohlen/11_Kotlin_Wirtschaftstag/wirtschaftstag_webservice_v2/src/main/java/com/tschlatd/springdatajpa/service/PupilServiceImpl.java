package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Pupil;
import com.tschlatd.springdatajpa.repository.PupilRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {
    @Autowired
    @Getter
    @Setter
    private PupilRepository repo;

    @Override
    public List<Pupil> getAllPupils() {
        return repo.findAll();
    }

    @Override
    public Pupil getOnePupil(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Pupil createPupil(Pupil Pupil) {
        return repo.save(Pupil);
    }

    @Override
    public boolean deletePupil(int id) {
        repo.deleteById(id);
        return true;
    }
}
package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Responsible;
import com.tschlatd.springdatajpa.repository.ResponsibleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsibleServiceImpl implements ResponsibleService {
    @Autowired
    @Getter
    @Setter
    private ResponsibleRepository repo;

    @Override
    public List<Responsible> getAllResponsibles() {
        return repo.findAll();
    }

    @Override
    public Responsible getOneResponsible(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Responsible createResponsible(Responsible Responsible) {
        return repo.save(Responsible);
    }

    @Override
    public boolean deleteResponsible(int id) {
        repo.deleteById(id);
        return true;
    }
}
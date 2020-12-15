package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Participation;
import com.scarc.springbootjparest.repository.EventRepository;
import com.scarc.springbootjparest.repository.ParticipationRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParticipationServiceImpl implements ParticipationService {
    @Autowired
    @Getter
    @Setter
    private ParticipationRepository repo;

    @Override
    public List<Participation> getAllParticipations() {
        return repo.findAll();
    }

    @Override
    public Participation getOneParticipation(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Participation createParticipation(Participation Participation) {
        return repo.save(Participation);
    }

    @Override
    public boolean deleteParticipation(int id) {
        repo.deleteById(id);
        return true;
    }
}

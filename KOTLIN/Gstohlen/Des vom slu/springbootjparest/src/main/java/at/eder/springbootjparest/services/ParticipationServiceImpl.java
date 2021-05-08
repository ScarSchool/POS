package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.Participation;
import at.scarc.springbootjparest.repositories.MailRepository;
import at.scarc.springbootjparest.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService {

    @Autowired
    private ParticipationRepository repo;

    @Override
    public List<Participation> getAll() {
        return repo.findAll();
    }

    @Override
    public Participation getOne(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Participation createOrUpdateOne(Participation entity) {
        return repo.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}

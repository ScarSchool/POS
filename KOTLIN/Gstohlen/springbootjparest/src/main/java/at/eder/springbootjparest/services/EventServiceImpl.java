package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.Event;
import at.scarc.springbootjparest.repositories.CompanyRepository;
import at.scarc.springbootjparest.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repo;

    @Override
    public List<Event> getAll() {
        return repo.findAll();
    }

    @Override
    public Event getOne(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Event createOrUpdateOne(Event entity) {
        return repo.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}

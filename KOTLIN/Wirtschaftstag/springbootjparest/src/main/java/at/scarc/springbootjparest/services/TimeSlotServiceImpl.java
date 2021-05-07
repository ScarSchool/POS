package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.TimeSlot;
import at.scarc.springbootjparest.repositories.MailRepository;
import at.scarc.springbootjparest.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    @Autowired
    private TimeSlotRepository repo;

    @Override
    public List<TimeSlot> getAll() {
        return repo.findAll();
    }

    @Override
    public TimeSlot getOne(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public TimeSlot createOrUpdateOne(TimeSlot entity) {
        return repo.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        repo.deleteById(id);
        return true;
    }
}

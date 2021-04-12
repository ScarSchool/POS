package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.TimeSlot;
import com.scarc.springbootjparest.repository.EventRepository;
import com.scarc.springbootjparest.repository.TimeSlotRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    @Getter
    @Setter
    private TimeSlotRepository repo;

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        return repo.findAll();
    }

    @Override
    public TimeSlot getOneTimeSlot(int id) {
        return repo.findById(id).get();
    }

    @Override
    public TimeSlot createTimeSlot(TimeSlot TimeSlot) {
        return repo.save(TimeSlot);
    }

    @Override
    public boolean deleteTimeSlot(int id) {
        repo.deleteById(id);
        return true;
    }
}

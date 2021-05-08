package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.TimeSlot;
import com.tschlatd.springdatajpa.repository.TimeSlotRepository;
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
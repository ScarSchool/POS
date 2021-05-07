package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.TimeSlot;
import com.tschlatd.springdatajpa.service.TimeSlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/timeslots")
public class TimeSlotController {
    @Autowired
    private TimeSlotServiceImpl service;

    @GetMapping("")
    public List<TimeSlot> getAllTimeSlots() {
        return service.getAllTimeSlots();
    }

    @GetMapping("/{id}")
    public TimeSlot getTimeSlotById(@PathVariable int id) {
        return service.getOneTimeSlot(id);
    }

    @PostMapping("")
    public TimeSlot createTimeSlot(@RequestBody TimeSlot TimeSlot) {
        return service.createTimeSlot(TimeSlot);
    }

    @PutMapping("")
    public TimeSlot updateTimeSlot(@RequestBody TimeSlot TimeSlot) {
        return service.createTimeSlot(TimeSlot);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeSlot(@PathVariable int id) {
        service.deleteTimeSlot(id);
    }
}
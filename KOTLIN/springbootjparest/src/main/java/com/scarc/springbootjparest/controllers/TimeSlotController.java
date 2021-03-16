package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.TimeSlot;
import com.scarc.springbootjparest.services.TimeSlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TimeSlots")
public class TimeSlotController {
    @Autowired
    private TimeSlotServiceImpl svc;

    @GetMapping("/")
    public List<TimeSlot> getAllTimeSlots() {
        return svc.getAllTimeSlots();
    }

    @GetMapping("/{id}")
    public TimeSlot getTimeSlotById(@PathVariable int id) {
        return svc.getOneTimeSlot(id);
    }

    @PostMapping("/")
    public TimeSlot createTimeSlot(@RequestBody TimeSlot TimeSlot) {
        return svc.createTimeSlot(TimeSlot);
    }

    @PutMapping("/{id}")
    public TimeSlot updateTimeSlot(@RequestBody TimeSlot TimeSlot) {
        return svc.createTimeSlot(TimeSlot);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeSlot(@PathVariable int id) {
        svc.deleteTimeSlot(id);
    }
}
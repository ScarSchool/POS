package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    List<TimeSlot> getAllTimeSlots();
    TimeSlot getOneTimeSlot(int id);
    TimeSlot createTimeSlot(TimeSlot TimeSlot);
    boolean deleteTimeSlot(int id);
}



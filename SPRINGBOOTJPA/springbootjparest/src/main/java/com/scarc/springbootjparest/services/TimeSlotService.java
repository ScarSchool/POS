package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.TimeSlot;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TimeSlotService {
    List<TimeSlot> getAllTimeSlots();
    TimeSlot getOneTimeSlot(int id);
    TimeSlot createTimeSlot(TimeSlot TimeSlot);
    boolean deleteTimeSlot(int id);
}

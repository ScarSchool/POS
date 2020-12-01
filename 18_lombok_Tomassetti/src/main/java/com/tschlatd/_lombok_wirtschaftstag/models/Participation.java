package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    private double price;
    private double paidAlready;
    private String comments;

    private Event at;
    private Responsible responsible;
    private Department wantsToParticipate;
    private List<TimeSlot> timeSlots;
}

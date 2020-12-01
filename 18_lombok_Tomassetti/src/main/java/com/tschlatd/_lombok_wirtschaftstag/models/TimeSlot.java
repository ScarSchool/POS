package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {
    LocalTime starts;
    LocalTime ends;
    int maxParticipants;
}

package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Pupil extends User {
    private List<TimeSlot> joins;
}

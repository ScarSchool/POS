package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private LocalDate date;
    private String label;
    private double defaultPrice;

    private Admin organizer;
}

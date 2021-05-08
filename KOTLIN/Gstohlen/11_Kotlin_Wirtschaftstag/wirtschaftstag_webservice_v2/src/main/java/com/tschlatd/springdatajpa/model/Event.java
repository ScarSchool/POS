package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String label;

    @NonNull
    @Column(nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate date;

    @NonNull
    @Column(nullable = false)
    private double defaultPrice;

    @ManyToOne
    private Admin organiser;

}

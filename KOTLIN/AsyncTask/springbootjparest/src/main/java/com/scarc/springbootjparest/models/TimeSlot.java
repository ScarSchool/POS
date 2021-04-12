package com.scarc.springbootjparest.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Builder
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    LocalTime starts;
    LocalTime ends;
    int maxParticipants;

    @ManyToOne
    private Participation participation;

    @ManyToMany
    private List<Pupil> pupils;
}

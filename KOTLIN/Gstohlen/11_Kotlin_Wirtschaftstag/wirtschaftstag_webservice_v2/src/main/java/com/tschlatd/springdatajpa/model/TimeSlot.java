package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@SuperBuilder
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @NonNull
    @Column(nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalTime starts;

    @NonNull
    @Column(nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalTime ends;

    @NonNull
    @Column(nullable = false)
    private int maxParticipants;

    @ManyToOne
    @JoinColumn(name = "participation_id")
    @JsonIgnoreProperties("slots")
    private Participation participation;

    @ManyToMany(mappedBy = "joins")
    @JsonIgnoreProperties("joins")
    Set<Pupil> participants;

}
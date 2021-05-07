package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false)
    private double price;

    @NonNull
    @Column(nullable = false)
    private double paidAlready;

    @NonNull
    @Column(nullable = false)
    private String comments;

    @ManyToOne
    private Event at;

    @ManyToOne
    private Responsible responsible;

    @ManyToOne
    private Company company;

    @ManyToMany
    private Set<Department> interestedIn;

    @OneToMany(mappedBy = "participation")
    @JsonIgnoreProperties("participation")
    private Set<TimeSlot> slots;

}

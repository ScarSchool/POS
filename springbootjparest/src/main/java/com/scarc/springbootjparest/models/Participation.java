package com.scarc.springbootjparest.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;
    private double paidAlready;
    private String comments;

    @ManyToOne
    private Event eventAt;
    @ManyToOne
    private Responsible responsible;
    @ManyToOne
    private Department wantsToParticipate;
    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "participation")
    private List<TimeSlot> timeSlots;
}

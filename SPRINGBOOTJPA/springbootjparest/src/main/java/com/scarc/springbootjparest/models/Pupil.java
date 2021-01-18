package com.scarc.springbootjparest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pupil extends User {
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "joins",
            joinColumns = @JoinColumn(name = "pupil_id"),
            inverseJoinColumns = @JoinColumn(name = "timeslot_id"))
    private List<TimeSlot> joins;
}

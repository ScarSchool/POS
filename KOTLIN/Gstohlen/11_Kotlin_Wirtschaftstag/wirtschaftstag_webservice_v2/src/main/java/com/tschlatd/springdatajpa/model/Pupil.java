package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@ToString(callSuper=true)
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("pupil")
public class Pupil extends User {

    @ManyToMany
    @JoinTable(name = "user_joins")
    @JsonIgnoreProperties("participants")
    Set<TimeSlot> joins;

}

package com.scarc.springbootjparest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalTime starts;
  private LocalTime ends;
  private int maxParticipants;

  @ManyToOne
  private Participation participation;

  @ManyToMany
  private List<Pupil> pupils;
}

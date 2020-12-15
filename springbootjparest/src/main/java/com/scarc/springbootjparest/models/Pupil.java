package com.scarc.springbootjparest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Pupil {


  @ManyToMany
  @JoinTable(
          name = "joins",
          joinColumns = @JoinColumn(name = "pupil_id"),
          inverseJoinColumns = @JoinColumn(name = "timeslot_id"))
  private List<TimeSlot> joins;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}

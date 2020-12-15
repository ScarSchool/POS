package com.scarc.springbootjparest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Participation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private double price;
  private double paidAlready;
  private String comments;

  @ManyToOne
  private Event at;

  @ManyToOne
  private Responsible responsible;

  @ManyToOne
  private Department interestedIn;

  @ManyToOne
  private Company company;

  @OneToMany(mappedBy = "participation", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<TimeSlot> slots;

}

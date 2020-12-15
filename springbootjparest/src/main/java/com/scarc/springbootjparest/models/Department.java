package com.scarc.springbootjparest.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String label;

  @OneToMany(mappedBy = "interestedIn", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Participation> participations;

}

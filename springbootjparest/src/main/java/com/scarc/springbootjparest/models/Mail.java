package com.scarc.springbootjparest.models;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int nr;
  private LocalDate date;
  private LocalTime time;
  private String subject;
  private String content;
  private File attachment1;
  private File attachment2;
  private File attachment3;

  @ManyToOne
  private Admin sender;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "mail", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Responsible> receivers;

}

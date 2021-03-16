package com.scarc.springbootjparest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Builder
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nr;
    private LocalDate date;
    private LocalTime time;
    private String subject;
    private String content;

    private File attachment1;
    private File attachment2;
    private File attachment3;

    @OneToMany(mappedBy = "mail")
    private List<Responsible> recipients;

    @ManyToOne
    private Admin sender;
}

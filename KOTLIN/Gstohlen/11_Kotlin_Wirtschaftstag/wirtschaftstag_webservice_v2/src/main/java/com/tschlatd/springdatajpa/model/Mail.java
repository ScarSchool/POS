package com.tschlatd.springdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate at;

    @NonNull
    @Column(nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalTime time;

    @NonNull
    @Column(nullable = false)
    private String subject;

    @NonNull
    @Column(nullable = false)
    private String content;

    private File attachment1;

    private File attachment2;

    private File attachment3;

    @ManyToOne
    private Admin sender;

    @ManyToMany
    @JoinTable(name = "mail_receivers")
    @JsonIgnoreProperties("mails")
    private Set<Responsible> receivers;

}

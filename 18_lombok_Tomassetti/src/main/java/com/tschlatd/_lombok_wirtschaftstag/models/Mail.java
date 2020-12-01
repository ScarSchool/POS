package com.scarc._lombok_tomassetti.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private int nr;
    private LocalDate at;
    private LocalTime when;
    private String subject;
    private String content;

    private File attachment1;
    private File attachment2;
    private File attachment3;

    private List<Responsible> recipients;
    private Admin sender;
}

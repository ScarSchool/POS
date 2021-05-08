package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Participation;

import java.util.List;

public interface ParticipationService {
    List<Participation> getAllParticipations();
    Participation getOneParticipation(int id);
    Participation createParticipation(Participation Participation);
    boolean deleteParticipation(int id);
}

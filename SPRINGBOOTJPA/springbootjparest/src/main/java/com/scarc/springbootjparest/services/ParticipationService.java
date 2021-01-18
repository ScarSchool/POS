package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Participation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ParticipationService {
    List<Participation> getAllParticipations();
    Participation getOneParticipation(int id);
    Participation createParticipation(Participation Participation);
    boolean deleteParticipation(int id);
}

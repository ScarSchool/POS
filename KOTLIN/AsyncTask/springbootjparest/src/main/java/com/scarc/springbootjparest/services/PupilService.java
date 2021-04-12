package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Pupil;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PupilService {
    List<Pupil> getAllPupils();
    Pupil getOnePupil(int id);
    Pupil createPupil(Pupil Pupil);
    boolean deletePupil(int id);
}

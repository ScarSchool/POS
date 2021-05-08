package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Pupil;

import java.util.List;

public interface PupilService {
    List<Pupil> getAllPupils();
    Pupil getOnePupil(int id);
    Pupil createPupil(Pupil Pupil);
    boolean deletePupil(int id);
}

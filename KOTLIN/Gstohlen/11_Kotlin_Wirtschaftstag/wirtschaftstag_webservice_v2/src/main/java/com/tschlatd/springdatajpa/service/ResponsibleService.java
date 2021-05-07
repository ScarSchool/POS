package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Responsible;

import java.util.List;

public interface ResponsibleService {
    List<Responsible> getAllResponsibles();
    Responsible getOneResponsible(int id);
    Responsible createResponsible(Responsible Responsible);
    boolean deleteResponsible(int id);
}

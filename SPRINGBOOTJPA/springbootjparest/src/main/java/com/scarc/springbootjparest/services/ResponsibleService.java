package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Responsible;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ResponsibleService {
    List<Responsible> getAllResponsibles();
    Responsible getOneResponsible(int id);
    Responsible createResponsible(Responsible Responsible);
    boolean deleteResponsible(int id);
}

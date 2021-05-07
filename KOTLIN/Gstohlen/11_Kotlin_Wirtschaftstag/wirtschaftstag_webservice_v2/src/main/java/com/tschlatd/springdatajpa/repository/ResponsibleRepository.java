package com.tschlatd.springdatajpa.repository;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Integer> {
}

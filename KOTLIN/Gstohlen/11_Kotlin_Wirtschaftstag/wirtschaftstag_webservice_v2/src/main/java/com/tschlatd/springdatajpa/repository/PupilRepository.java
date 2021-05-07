package com.tschlatd.springdatajpa.repository;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {
}

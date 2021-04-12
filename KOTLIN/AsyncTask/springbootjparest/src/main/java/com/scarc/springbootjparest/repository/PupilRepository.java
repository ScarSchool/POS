package com.scarc.springbootjparest.repository;

import com.scarc.springbootjparest.models.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PupilRepository extends JpaRepository<Pupil, Integer> {
}

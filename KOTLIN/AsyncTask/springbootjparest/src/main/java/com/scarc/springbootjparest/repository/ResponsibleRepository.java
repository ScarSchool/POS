package com.scarc.springbootjparest.repository;

import com.scarc.springbootjparest.models.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Integer> {
}

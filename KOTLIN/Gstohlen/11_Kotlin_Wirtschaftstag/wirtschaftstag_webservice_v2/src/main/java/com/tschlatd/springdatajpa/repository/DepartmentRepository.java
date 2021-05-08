package com.tschlatd.springdatajpa.repository;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

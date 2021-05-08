package com.tschlatd.springdatajpa.repository;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
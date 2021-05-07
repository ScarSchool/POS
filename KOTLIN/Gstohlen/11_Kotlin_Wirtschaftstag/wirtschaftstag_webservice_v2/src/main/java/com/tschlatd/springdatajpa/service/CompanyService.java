package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getOneCompany(int id);
    Company createCompany(Company Company);
    boolean deleteCompany(int id);
}

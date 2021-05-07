package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Company;
import com.tschlatd.springdatajpa.repository.CompanyRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    @Getter
    @Setter
    private CompanyRepository repo;

    @Override
    public List<Company> getAllCompanies() {
        return repo.findAll();
    }

    @Override
    public Company getOneCompany(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Company createCompany(Company Company) {
        return repo.save(Company);
    }

    @Override
    public boolean deleteCompany(int id) {
        repo.deleteById(id);
        return true;
    }
}
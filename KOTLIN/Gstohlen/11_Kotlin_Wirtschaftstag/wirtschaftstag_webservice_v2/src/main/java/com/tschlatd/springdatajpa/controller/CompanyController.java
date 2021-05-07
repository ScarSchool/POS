package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Company;
import com.tschlatd.springdatajpa.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl service;

    @GetMapping("")
    public List<Company> getAllCompanys() {
        return service.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return service.getOneCompany(id);
    }

    @PostMapping("")
    public Company createCompany(@RequestBody Company company) { return service.createCompany(company); }

    @PutMapping("")
    public Company updateCompany(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        service.deleteCompany(id);
    }
}
package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Company;
import com.scarc.springbootjparest.services.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Companys")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl svc;

    @GetMapping("/")
    public List<Company> getAllCompanys() {
        return svc.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return svc.getOneCompany(id);
    }

    @PostMapping("/")
    public Company createCompany(@RequestBody Company Company) {
        return svc.createCompany(Company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestBody Company Company) {
        return svc.createCompany(Company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable int id) {
        svc.deleteCompany(id);
    }
}
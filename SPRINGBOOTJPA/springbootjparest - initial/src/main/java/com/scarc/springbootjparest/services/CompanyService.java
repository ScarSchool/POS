package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Company;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {
    List<Company> getAllCompanies();
    Company getOneCompany(int id);
    Company createCompany(Company Company);
    boolean deleteCompany(int id);
}

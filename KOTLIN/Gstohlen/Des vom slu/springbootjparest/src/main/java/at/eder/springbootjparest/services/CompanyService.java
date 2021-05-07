package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.Company;
import at.scarc.springbootjparest.repositories.CompanyRepository;
import at.scarc.springbootjparest.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService extends DefaultService<Company> {

}

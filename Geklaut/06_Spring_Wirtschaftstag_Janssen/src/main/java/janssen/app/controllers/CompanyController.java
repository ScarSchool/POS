package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.CompanyDAO;
import janssen.app.models.Company;

import janssen.app.models.Department;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

  private final CompanyDAO dao;

  public CompanyController(CompanyDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<Company> listCompanies() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public Company getCompany(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public Company createCompany(@RequestBody Company company) {
    return dao.save(company);
  }

  @PutMapping({"", "{id}"})
  public Company updateCompany(@PathVariable Optional<String> id, @RequestBody Company company) {
    id.ifPresent(idVal -> company.setId( Integer.parseInt(idVal) ));
    return dao.save(company);
  }

  @DeleteMapping("{id}")
  public void deleteCompany(@PathVariable String id) {
    Company company = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(company);
  }

}

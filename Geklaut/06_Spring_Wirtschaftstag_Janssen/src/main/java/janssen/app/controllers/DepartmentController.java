package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.DepartmentDAO;
import janssen.app.models.Department;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

  private final DepartmentDAO dao;

  public DepartmentController(DepartmentDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<Department> listDepartments() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public Department getDepartment(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public Department createDepartment(@RequestBody Department department) {
    return dao.save(department);
  }

  @PutMapping({"", "{id}"})
  public Department updateDepartment(@PathVariable Optional<String> id, @RequestBody Department department) {
    id.ifPresent(idVal -> department.setId( Integer.parseInt(idVal) ));
    return dao.save(department);
  }

  @DeleteMapping("{id}")
  public void deleteDepartment(@PathVariable String id) {
    Department department = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(department);
  }

}

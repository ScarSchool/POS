package at.scarc.springbootjparest.controllers;

import at.scarc.springbootjparest.models.Department;
import at.scarc.springbootjparest.services.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl svc;

    @GetMapping()
    public List<Department> getAllDepartments() { return svc.getAll(); }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) { return svc.getOne(id); }

    @PostMapping()
    public Department createDepartment(@RequestBody Department department) { return svc.createOrUpdateOne(department); }

    @PutMapping()
    public Department updateDepartment(@RequestBody Department department) { return svc.createOrUpdateOne(department); }

    @DeleteMapping("/{id}")
    public boolean deleteDepartment(@PathVariable Long id) { return svc.delete(id); }
}

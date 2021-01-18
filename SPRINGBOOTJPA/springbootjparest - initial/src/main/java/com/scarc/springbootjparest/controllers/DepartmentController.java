package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Department;
import com.scarc.springbootjparest.services.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Departments")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl svc;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return svc.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id) {
        return svc.getOneDepartment(id);
    }

    @PostMapping("/")
    public Department createDepartment(@RequestBody Department Department) {
        return svc.createDepartment(Department);
    }

    @PutMapping("/")
    public Department updateDepartment(@RequestBody Department Department) {
        return svc.createDepartment(Department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id) {
        svc.deleteDepartment(id);
    }
}
package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Department;
import com.tschlatd.springdatajpa.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl service;

    @GetMapping("")
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id) {
        return service.getOneDepartment(id);
    }

    @PostMapping("")
    public Department createDepartment(@RequestBody Department Department) {
        return service.createDepartment(Department);
    }

    @PutMapping("")
    public Department updateDepartment(@RequestBody Department Department) {
        return service.createDepartment(Department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable int id) {
        service.deleteDepartment(id);
    }
}
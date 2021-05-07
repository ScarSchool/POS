package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getOneDepartment(int id);
    Department createDepartment(Department Department);
    boolean deleteDepartment(int id);
}

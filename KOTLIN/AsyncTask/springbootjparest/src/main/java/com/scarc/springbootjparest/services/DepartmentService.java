package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Department;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getOneDepartment(int id);
    Department createDepartment(Department Department);
    boolean deleteDepartment(int id);
}

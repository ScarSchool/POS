package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Department;
import com.scarc.springbootjparest.repository.CompanyRepository;
import com.scarc.springbootjparest.repository.DepartmentRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    @Getter
    @Setter
    private DepartmentRepository repo;

    @Override
    public List<Department> getAllDepartments() {
        return repo.findAll();
    }

    @Override
    public Department getOneDepartment(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Department createDepartment(Department Department) {
        return repo.save(Department);
    }

    @Override
    public boolean deleteDepartment(int id) {
        repo.deleteById(id);
        return true;
    }
}

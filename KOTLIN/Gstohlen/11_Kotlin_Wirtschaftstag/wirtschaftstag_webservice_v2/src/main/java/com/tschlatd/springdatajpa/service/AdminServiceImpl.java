package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Admin;
import com.tschlatd.springdatajpa.repository.AdminRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    @Getter
    @Setter
    private AdminRepository repo;

    @Override
    public List<Admin> getAllAdmins() { return repo.findAll(); }

    @Override
    public Admin getOneAdmin(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return repo.save(admin);
    }

    @Override
    public boolean deleteAdmin(int id) {
        repo.deleteById(id);
        return true;
    }
}

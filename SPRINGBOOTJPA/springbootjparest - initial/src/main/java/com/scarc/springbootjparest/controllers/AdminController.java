package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Admin;
import com.scarc.springbootjparest.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminServiceImpl svc;

    @GetMapping("/")
    public List<Admin> getAllAdmins() {
        return svc.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable int id) {
        return svc.getOneAdmin(id);
    }

    @PostMapping("/")
    public Admin createAdmin(@RequestBody Admin Admin) {
        return svc.createAdmin(Admin);
    }

    @PutMapping("/")
    public Admin updateAdmin(@RequestBody Admin Admin) {
        return svc.createAdmin(Admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable int id) {
        svc.deleteAdmin(id);
    }
}
package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getOneAdmin(int id);
    Admin createAdmin(Admin admin);
    boolean deleteAdmin(int id);
}

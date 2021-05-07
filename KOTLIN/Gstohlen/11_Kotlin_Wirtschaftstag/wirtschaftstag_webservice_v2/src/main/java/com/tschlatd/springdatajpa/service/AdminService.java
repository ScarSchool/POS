package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getOneAdmin(int id);
    Admin createAdmin(Admin admin);
    boolean deleteAdmin(int id);
}

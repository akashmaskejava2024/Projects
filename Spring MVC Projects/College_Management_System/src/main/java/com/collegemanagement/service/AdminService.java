package com.collegemanagement.service;

import com.collegemanagement.dao.AdminDAO;
import com.collegemanagement.entity.Admin;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void addAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    public void deleteAdmin(int id) {
        adminDAO.deleteAdmin(id);
    }
    
    public Admin validateAdmin(String email, String password) {
        return adminDAO.findAdminByEmailAndPassword(email, password);
    }
}
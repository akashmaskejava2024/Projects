package com.collegemanagement.dao;

import com.collegemanagement.entity.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAO {
    private final JdbcTemplate jdbcTemplate;

    public AdminDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Admin findAdminByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM admin WHERE email = ? AND password = ? AND active = TRUE";
        
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Admin(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password")
            ), email, password);
        } catch (Exception e) {
            return null;
        }
    }

    // Add Admin
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admin (email, password, active) VALUES (?, ?, TRUE)";
        jdbcTemplate.update(sql, admin.getEmail(), admin.getPassword());
    }

    // Get Admin by ID
    public Admin getAdminById(int id) {
        String sql = "SELECT * FROM admin WHERE id = ? AND active = TRUE";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, adminRowMapper());
    }

    // Get All Active Admins
    public List<Admin> getAllAdmins() {
        String sql = "SELECT * FROM admin WHERE active = TRUE";
        return jdbcTemplate.query(sql, adminRowMapper());
    }

    // Update Admin
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE admin SET email = ?, password = ? WHERE id = ? AND active = TRUE";
        jdbcTemplate.update(sql, admin.getEmail(), admin.getPassword(), admin.getId());
    }

    // Soft Delete Admin
    public void deleteAdmin(int id) {
        String sql = "UPDATE admin SET active = FALSE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Admin> adminRowMapper() {
        return (rs, rowNum) -> new Admin(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
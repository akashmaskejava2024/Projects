package com.collegemanagement.dao;

import com.collegemanagement.entity.Parent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParentDAO {
    private final JdbcTemplate jdbcTemplate;

    public ParentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔹 Add Parent
    public int addParent(Parent parent) {
        String sql = "INSERT INTO parent (student_id, name, email, password, phone_number, active) VALUES (?, ?, ?, ?, ?, true)";
        return jdbcTemplate.update(sql, parent.getStudentId(), parent.getName(), parent.getEmail(), parent.getPassword(), parent.getPhoneNumber());
    }

    // 🔹 Get Parent by ID (Only active)
    public Parent getParentById(int id) {
        String sql = "SELECT * FROM parent WHERE id = ? AND active = true";
        List<Parent> parents = jdbcTemplate.query(sql, new ParentRowMapper(), id);
        return parents.isEmpty() ? null : parents.get(0);
    }

    // 🔹 Get Parent by Student ID (Only active)
    public Parent getParentByStudentId(int studentId) {
        String sql = "SELECT * FROM parent WHERE student_id = ? AND active = true";
        List<Parent> parents = jdbcTemplate.query(sql, new ParentRowMapper(), studentId);
        return parents.isEmpty() ? null : parents.get(0);
    }

    // 🔹 Get all Parents (Only active)
    public List<Parent> getAllParents() {
        String sql = "SELECT * FROM parent WHERE active = true";
        return jdbcTemplate.query(sql, new ParentRowMapper());
    }

    // 🔹 Update Parent details
    public int updateParent(Parent parent) {
        String sql = "UPDATE parent SET name = ?, email = ?, password = ?, phone_number = ? WHERE id = ?";
        return jdbcTemplate.update(sql, parent.getName(), parent.getEmail(), parent.getPassword(), parent.getPhoneNumber(), parent.getId());
    }

    // 🔹 Delete Parent (Soft delete)
    public int deleteParent(int id) {
        String sql = "UPDATE parent SET active = false WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 🔹 Find Parent by Email (Only active)
    public Parent findByEmail(String email) {
        String sql = "SELECT * FROM parent WHERE email = ? AND active = true";
        List<Parent> parents = jdbcTemplate.query(sql, new ParentRowMapper(), email);
        return parents.isEmpty() ? null : parents.get(0);
    }

    // 🔹 RowMapper for Parent Entity
    private static class ParentRowMapper implements RowMapper<Parent> {
        @Override
        public Parent mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Parent(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("phone_number")
            );
        }
    }
}
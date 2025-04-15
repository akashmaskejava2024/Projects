
package com.nt.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nt.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // **Signup (Insert Student into Database)**
    public int registerStudent(Student student) {
        String sql = "INSERT INTO student (name, email, password, professorId) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getPassword(), student.getProfessorId());
    }

    // **Login (Find Student by Email & Password)**
    public Student loginStudent(String email, String password) {
        String sql = "SELECT * FROM student WHERE email = ? AND password = ?";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper(), email, password);

        return students.isEmpty() ? null : students.get(0);
    }

    // **Find Student by ID**
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), studentId);
    }

    // **Check if Email Already Exists (for Signup Validation)**
    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM student WHERE email = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
    
    
    

private static class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getLong("professorId")
        );
    }
}
}
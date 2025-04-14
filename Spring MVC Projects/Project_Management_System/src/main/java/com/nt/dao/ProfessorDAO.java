package com.nt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.nt.entity.Professor;
import com.nt.entity.ProjectStatus;

@Repository
public class ProfessorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ✅ Register a professor (Ensures unique email)
    public boolean registerProfessor(Professor professor) {
        String sql = "INSERT INTO Professor (name, email, password) VALUES (?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql, professor.getName(), professor.getEmail(), professor.getPassword());
            return result > 0;
        } catch (Exception e) {
            return false; // Handle duplicate email issue
        }
    }


public Professor validateProfessor(String email, String password) {
    String sql = "SELECT * FROM Professor WHERE email = ? AND password = ?";
    
    try {
        return jdbcTemplate.queryForObject(sql, new ProfessorRowMapper(), email, password);
    } catch (EmptyResultDataAccessException e) {
        return null; // ❌ No user found or incorrect credentials
    }
}
    // ✅ Get projects of students assigned to the professor
public List<ProjectStatus> getProjectsOfStudents(Long professorId) {
    String sql = "SELECT ps.* FROM ProjectStatus ps " +
                 "JOIN Student s ON ps.studentId = s.id " +
                 "WHERE s.professorId = ?";

    return jdbcTemplate.query(sql, new ProjectStatusRowMapper(), professorId);
}

    // ✅ Add feedback to a project status
    public void addFeedback(Long projectStatusId, String feedback) {
        String sql = "UPDATE ProjectStatus SET feedback = ? WHERE id = ?";
        jdbcTemplate.update(sql, feedback, projectStatusId);
    }
    
    // ✅ RowMapper for Professor
    private static class ProfessorRowMapper implements RowMapper<Professor> {
        @Override
        public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
            Professor professor = new Professor();
            professor.setId(rs.getLong("id"));
            professor.setName(rs.getString("name"));
            professor.setEmail(rs.getString("email"));
            professor.setPassword(rs.getString("password"));
            return professor;
        }
    }

    // ✅ RowMapper for ProjectStatus
    private static class ProjectStatusRowMapper implements RowMapper<ProjectStatus> {
        @Override
        public ProjectStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProjectStatus status = new ProjectStatus();
            status.setId(rs.getLong("id"));
            status.setProjectId(rs.getLong("projectId"));
            status.setStudentId(rs.getLong("studentId"));
            status.setPhase(rs.getString("phase"));
            status.setUiImage(rs.getString("uiImage"));
            status.setUpdateDescription(rs.getString("updateDescription"));
            status.setFeedback(rs.getString("feedback"));
            status.setUpdatedAt(rs.getTimestamp("updatedAt"));
            return status;
        }
    }
}
package com.nt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.entity.Project;
@Repository
public class ProjectDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveProject(Project project) {
        String sql = "INSERT INTO Project (title, description, studentId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, project.getTitle(), project.getDescription(), project.getStudentId());
    }
    

    public Project getProjectByStudentId(Long studentId) {
        String sql = "SELECT * FROM Project WHERE studentId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Project.class), studentId);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no project exists for the student
        }
    }
}
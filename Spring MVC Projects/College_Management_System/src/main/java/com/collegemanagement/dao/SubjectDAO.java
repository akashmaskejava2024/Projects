package com.collegemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.collegemanagement.entity.Subject;

@Repository
public class SubjectDAO {
    private final JdbcTemplate jdbcTemplate;

    public SubjectDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subject> getSubjectsBySemester(int semester) {
        String sql = "SELECT * FROM subject WHERE semester = ? AND active = TRUE";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Subject(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("course_id"),
            rs.getInt("semester")
        ), semester);
    }

    public List<Subject> getSubjectsByCourse(int courseId) {
        String sql = "SELECT * FROM subject WHERE course_id = ? AND active = TRUE ORDER BY semester";
        return jdbcTemplate.query(sql, new SubjectRowMapper(), courseId);
    }

    private final RowMapper<Subject> subjectRowMapper = (rs, rowNum) ->
        new Subject(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("course_id"),
            rs.getInt("semester")
        );

    public List<Subject> findByCourseIdAndSemester(int courseId, int semester) {
        String sql = "SELECT * FROM subject WHERE course_id = ? AND semester = ? AND active = TRUE";
        return jdbcTemplate.query(sql, subjectRowMapper, courseId, semester);
    }

    public void addSubject(Subject subject) {
        String sql = "INSERT INTO subject (name, course_id, semester, active) VALUES (?, ?, ?, TRUE)";
        jdbcTemplate.update(sql, subject.getName(), subject.getCourseId(), subject.getSemester());
    }

    public void deleteSubject(int id) {
        String sql = "UPDATE subject SET active = FALSE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Subject> getSubjectsByStudentId(int studentId) {
        String sql = "SELECT s.id, s.name, s.course_id, s.semester " +
                     "FROM subject s " +
                     "JOIN student_course sc ON s.course_id = sc.course_id " +
                     "WHERE sc.student_id = ? AND s.active = TRUE AND sc.active = TRUE";

        RowMapper<Subject> rowMapper = (rs, rowNum) -> new Subject(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("course_id"),
                rs.getInt("semester")
        );

        return jdbcTemplate.query(sql, rowMapper, studentId);
    }

    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT * FROM subject WHERE id = ? AND active = TRUE";
        return jdbcTemplate.queryForObject(sql, new Object[]{subjectId}, this::mapRowToSubject);
    }

    private Subject mapRowToSubject(ResultSet rs, int rowNum) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt("id"));
        subject.setName(rs.getString("name"));
        subject.setCourseId(rs.getInt("course_id"));
        subject.setSemester(rs.getInt("semester"));
        return subject;
    }
}

class SubjectRowMapper implements RowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getInt("id"));
        subject.setName(rs.getString("name"));
        subject.setCourseId(rs.getInt("course_id"));
        subject.setSemester(rs.getInt("semester"));
        return subject;
    }
}
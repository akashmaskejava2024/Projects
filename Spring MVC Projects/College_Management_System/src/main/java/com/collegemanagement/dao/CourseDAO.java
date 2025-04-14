package com.collegemanagement.dao;

import com.collegemanagement.entity.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CourseDAO {

    private final JdbcTemplate jdbcTemplate;

    public CourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Integer> getSemestersByCourseId(int courseId) {
        String sql = "SELECT semester FROM course WHERE id = ? AND active = true";
        Integer totalSemesters = jdbcTemplate.queryForObject(sql, Integer.class, courseId);
        if (totalSemesters == null) return Collections.emptyList();

        List<Integer> semesters = new ArrayList<>();
        for (int i = 1; i <= totalSemesters; i++) {
            semesters.add(i);
        }
        return semesters;
    }

    public int countCourses() {
        String sql = "SELECT COUNT(*) FROM course WHERE active = true";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM course WHERE active = true";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM course WHERE id = ? AND active = true";
        List<Course> courses = jdbcTemplate.query(sql, new CourseMapper(), courseId);
        return courses.isEmpty() ? null : courses.get(0);
    }

    public void addCourse(Course course) {
        String sql = "INSERT INTO course (name, semester, active) VALUES (?, ?, true)";
        jdbcTemplate.update(sql, course.getName(), course.getSemester());
    }

    public int updateCourse(Course course) {
        String sql = "UPDATE course SET name = ?, semester = ?, active = true WHERE id = ?";
        return jdbcTemplate.update(sql, course.getName(), course.getSemester(), course.getId());
    }

    public void deleteCourse(int id) {
        String sql = "UPDATE course SET active = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

// ✅ Renamed RowMapper to avoid conflict
class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setSemester(rs.getInt("semester"));
        return course;
    }
}
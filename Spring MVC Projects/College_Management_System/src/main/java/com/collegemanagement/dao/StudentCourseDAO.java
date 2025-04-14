package com.collegemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.StudentCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class StudentCourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StudentCourse findCourseByProfessorId(int professorId) {
        String sql = "SELECT sc.id, sc.student_id, sc.course_id, sc.professor_id, sc.semester " +
                     "FROM student_course sc WHERE sc.professor_id = ? AND sc.active = 1 LIMIT 1";

        return jdbcTemplate.query(sql, new Object[]{professorId}, rs -> {
            if (rs.next()) {
                return mapStudentCourse(rs);
            }
            return null;
        });
    }

    public Integer getSemesterByStudentAndProfessor(int studentId, int professorId) {
        try {
            return jdbcTemplate.queryForObject(
                "SELECT semester FROM student_course WHERE student_id = ? AND professor_id = ? AND active = 1",
                new Object[]{studentId, professorId},
                Integer.class
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int updateStudentCourse(int studentId, int courseId, int newProfessorId, int newSemester) {
        String sql = "UPDATE student_course SET professor_id = ?, semester = ? WHERE student_id = ? AND course_id = ? AND active = 1";
        return jdbcTemplate.update(sql, newProfessorId, newSemester, studentId, courseId);
    }

    public Map<Integer, List<Course>> getCoursesForStudents(List<Integer> studentIds, int semester) {
        if (studentIds == null || studentIds.isEmpty()) {
            return Collections.emptyMap();
        }

        String sql = "SELECT sc.student_id, c.id AS course_id, c.name, c.semester " +
                     "FROM student_course sc " +
                     "JOIN course c ON sc.course_id = c.id " +
                     "WHERE sc.student_id IN (" +
                     String.join(",", Collections.nCopies(studentIds.size(), "?")) + ") " +
                     "AND sc.semester = ? AND sc.active = 1";

        return jdbcTemplate.query(sql, preparedStatement -> {
            int index = 0;
            for (Integer id : studentIds) {
                preparedStatement.setInt(++index, id);
            }
            preparedStatement.setInt(++index, semester);
        }, extractStudentCourses());
    }

    public Map<Integer, List<Course>> getStudentsWithCourses(List<Integer> studentIds) {
        if (studentIds == null || studentIds.isEmpty()) {
            return Collections.emptyMap();
        }

        String sql = "SELECT sc.student_id, c.id AS course_id, c.name, c.semester " +
                     "FROM student_course sc " +
                     "JOIN course c ON sc.course_id = c.id " +
                     "WHERE sc.student_id IN (" +
                     String.join(",", Collections.nCopies(studentIds.size(), "?")) + ") " +
                     "AND sc.active = 1";

        return jdbcTemplate.query(sql, preparedStatement -> {
            int index = 0;
            for (Integer id : studentIds) {
                preparedStatement.setInt(++index, id);
            }
        }, extractStudentCourses());
    }

    private ResultSetExtractor<Map<Integer, List<Course>>> extractStudentCourses() {
        return rs -> {
            Map<Integer, List<Course>> studentCoursesMap = new HashMap<>();
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                Course course = new Course(
                    rs.getInt("course_id"),
                    rs.getString("name"),
                    rs.getInt("semester")
                );
                studentCoursesMap.computeIfAbsent(studentId, k -> new ArrayList<>()).add(course);
            }
            return studentCoursesMap;
        };
    }

    public List<StudentCourse> getCoursesByProfessorId(int professorId) {
        String sql = "SELECT id, student_id, course_id, semester, professor_id FROM student_course WHERE professor_id = ? AND active = 1";
        return jdbcTemplate.query(sql, new StudentCourseRowMapper(), professorId);
    }

    public int updateProfessorForCourse(int currentProfessorId, int newProfessorId, int courseId, int semester) {
        String sql = "UPDATE student_course SET professor_id = ? WHERE professor_id = ? AND course_id = ? AND semester = ? AND active = 1";
        return jdbcTemplate.update(sql, newProfessorId, currentProfessorId, courseId, semester);
    }

    public void assignCourseToStudent(int studentId, int courseId, int professorId, int semester) {
        String sql = "INSERT INTO student_course (student_id, course_id, professor_id, semester, active) VALUES (?, ?, ?, ?, 1)";
        jdbcTemplate.update(sql, studentId, courseId, professorId, semester);
    }

    public List<Course> getCoursesAssignedToStudent(int studentId, int semester) {
        String sql = "SELECT c.id, c.name, c.semester FROM course c " +
                     "JOIN student_course sc ON c.id = sc.course_id " +
                     "WHERE sc.student_id = ? AND sc.semester = ? AND sc.active = 1";
        return jdbcTemplate.query(sql, new Object[]{studentId, semester}, new CourseRowMapper());
    }

    public List<Course> getCoursesAssignedToStudent(int studentId) {
        String sql = "SELECT c.id, c.name, c.semester FROM course c " +
                     "JOIN student_course sc ON c.id = sc.course_id " +
                     "WHERE sc.student_id = ? AND sc.active = 1";
        return jdbcTemplate.query(sql, new Object[]{studentId}, new CourseRowMapper());
    }

    public StudentCourse getStudentCourseByStudentId(int studentId, int semester) {
        String sql = "SELECT id, student_id, course_id, professor_id, semester FROM student_course WHERE student_id = ? AND semester = ? AND active = 1";
        return jdbcTemplate.queryForObject(sql, new Object[]{studentId, semester}, new StudentCourseRowMapper());
    }

    public int softDeleteStudentCourse(int studentId, int courseId, int semester) {
        String sql = "UPDATE student_course SET active = 0 WHERE student_id = ? AND course_id = ? AND semester = ?";
        return jdbcTemplate.update(sql, studentId, courseId, semester);
    }

    private StudentCourse mapStudentCourse(ResultSet rs) throws SQLException {
        return new StudentCourse(
            rs.getInt("id"),
            rs.getInt("student_id"),
            rs.getInt("course_id"),
            rs.getInt("professor_id"),
            rs.getInt("semester")
        );
    }

    private static class StudentCourseRowMapper implements RowMapper<StudentCourse> {
        @Override
        public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new StudentCourse(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getInt("professor_id"),
                rs.getInt("semester")
            );
        }
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("semester")
            );
        }
    }
}
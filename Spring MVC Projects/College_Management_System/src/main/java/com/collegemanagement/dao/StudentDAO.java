package com.collegemanagement.dao;

import com.collegemanagement.entity.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countStudents() {
        String sql = "SELECT COUNT(*) FROM Student WHERE active = TRUE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int updateProfessorForStudents(int currentProfessorId, int newProfessorId, int courseId, int semester) {
        String sql = "UPDATE student SET professor_id = ? WHERE professor_id = ? AND id IN " +
                     "(SELECT student_id FROM student_course WHERE course_id = ? AND semester = ?) AND active = TRUE";
        return jdbcTemplate.update(sql, newProfessorId, currentProfessorId, courseId, semester);
    }

    public int updateStudentProfessor(int studentId, int newProfessorId) {
        String sql = "UPDATE student SET professor_id = ? WHERE id = ? AND active = TRUE";
        return jdbcTemplate.update(sql, newProfessorId, studentId);
    }

    public void deleteStudent(int id) {
        String sql = "UPDATE Student SET active = FALSE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void registerStudent(Student student) {
        String sql = "INSERT INTO student (name, email, password, roll_number, semester, professor_id, active) VALUES (?, ?, ?, ?, ?, ?, TRUE)";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getPassword(), student.getRollNumber(),
                student.getSemester(), student.getProfessorId());
    }

    public Student login(String email, String password) {
        String sql = "SELECT * FROM Student WHERE email = ? AND password = ? AND active = TRUE";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, new StudentRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM student WHERE id = ? AND active = TRUE";
        return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), studentId);
    }

    public List<Course> getCoursesByStudentId(int studentId) {
        String sql = "SELECT c.* FROM course c JOIN exam_score e ON c.id = e.course_id WHERE e.student_id = ?";
        return jdbcTemplate.query(sql, new CourseRowMapper(), studentId);
    }

    public List<ExamScore> getScoresByStudentId(int studentId) {
        String sql = "SELECT * FROM exam_score WHERE student_id = ?";
        return jdbcTemplate.query(sql, new ExamScoreRowMapper(), studentId);
    }

    public List<Activity> getActivitiesByStudentId(int studentId) {
        String sql = "SELECT * FROM activity WHERE student_id = ?";
        return jdbcTemplate.query(sql, new ActivityRowMapper(), studentId);
    }

    public List<Attendance> getAttendanceByStudentId(int studentId) {
        String sql = "SELECT * FROM attendance WHERE student_id = ?";
        return jdbcTemplate.query(sql, new AttendanceRowMapper(), studentId);
    }

    public int updateStudentProfile(Student student) {
        String sql = "UPDATE student SET name=?, email=?, roll_number=?, semester=? WHERE id=? AND active = TRUE";
        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getRollNumber(),
                student.getSemester(), student.getId());
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student WHERE active = TRUE";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
}

class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setPassword(rs.getString("password"));
        student.setRollNumber(rs.getString("roll_number"));
        student.setSemester(rs.getInt("semester"));
        student.setProfessorId(rs.getInt("professor_id"));
        return student;
    }
}

class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setSemester(rs.getInt("semester"));
        return course;
    }
}

class ExamScoreRowMapper implements RowMapper<ExamScore> {
    @Override
    public ExamScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamScore score = new ExamScore();
        score.setId(rs.getInt("id"));
        score.setStudentId(rs.getInt("student_id"));
        score.setCourseId(rs.getInt("course_id"));
        score.setSemester(rs.getInt("semester"));
        score.setMarks(rs.getInt("marks"));
        score.setTotalMarks(rs.getInt("total_marks"));
        return score;
    }
}

class ActivityRowMapper implements RowMapper<Activity> {
    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Activity activity = new Activity();
        activity.setId(rs.getInt("id"));
        activity.setStudentId(rs.getInt("student_id"));
        activity.setType(rs.getString("type"));
        activity.setSubtype(rs.getString("subtype"));
        activity.setCompetitionLevel(rs.getString("competition_level"));
        activity.setRank(rs.getInt("rank"));
        activity.setAchievement(rs.getString("achievement"));
        activity.setImage(rs.getString("image"));
        return activity;
    }
}

class AttendanceRowMapper implements RowMapper<Attendance> {
    @Override
    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(rs.getInt("id"));
        attendance.setStudentId(rs.getInt("student_id"));
        attendance.setDate(rs.getDate("date"));
        attendance.setStatus(rs.getString("status"));
        return attendance;
    }
}
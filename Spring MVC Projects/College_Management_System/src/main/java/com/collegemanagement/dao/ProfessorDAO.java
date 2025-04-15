package com.collegemanagement.dao;

import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Professor;
import com.collegemanagement.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int countProfessors() {
        String sql = "SELECT COUNT(*) FROM Professor WHERE active = TRUE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Custom RowMapper for Professor
    private final RowMapper<Professor> professorRowMapper = (rs, rowNum) -> new Professor(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("email"),
        rs.getString("password"),
        rs.getString("department") // Assuming department exists
    );

    // Check if email already exists (include only active records)
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM Professor WHERE email = ? AND active = TRUE";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    // Add new professor (sets active to true by default)
    public int addProfessor(Professor professor) {
        String sql = "INSERT INTO Professor (name, email, password, active) VALUES (?, ?, ?, TRUE)";
        return jdbcTemplate.update(sql, professor.getName(), professor.getEmail(), professor.getPassword());
    }

    // Get all professors (only active)
    public List<Professor> getAllProfessors() {
        String sql = "SELECT * FROM Professor WHERE active = TRUE";
        return jdbcTemplate.query(sql, professorRowMapper);
    }

    // Get professor by ID (only if active)
    public Optional<Professor> getProfessorById(int id) {
        String sql = "SELECT * FROM Professor WHERE id = ? AND active = TRUE";
        List<Professor> professors = jdbcTemplate.query(sql, professorRowMapper, id);
        return professors.stream().findFirst();
    }

    // Update professor
    public int updateProfessor(Professor professor) {
        String sql = "UPDATE Professor SET name = ?, email = ?, password = ? WHERE id = ? AND active = TRUE";
        return jdbcTemplate.update(sql, professor.getName(), professor.getEmail(), professor.getPassword(), professor.getId());
    }

    // 🔸 Soft delete professor (set active = false)
    public int deleteProfessor(int id) {
        String sql = "UPDATE Professor SET active = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Login only active professors
    public Optional<Professor> loginProfessor(String email, String password) {
        String sql = "SELECT * FROM Professor WHERE email = ? AND password = ? AND active = TRUE";
        List<Professor> professors = jdbcTemplate.query(sql, professorRowMapper, email, password);
        return professors.stream().findFirst();
    }

    // ✅ Fetch students assigned to a professor
    public List<Student> findStudentsByProfessorId(int professorId) {
        String sql = "SELECT * FROM student WHERE professor_id = ?";
        return jdbcTemplate.query(sql, new StudentRowMapper(), professorId);
    }

    public void insertExamScore(ExamScore examScore) {
        String sql = "INSERT INTO exam_score (student_id, course_id, semester, marks, total_marks) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, examScore.getStudentId(), examScore.getCourseId(), examScore.getSemester(), examScore.getMarks(), examScore.getTotalMarks());
    }

    public void markAttendance(int studentId, String date, String status) {
        String sql = "INSERT INTO Attendance (student_id, date, status) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE status = ?";
        jdbcTemplate.update(sql, studentId, date, status, status);
    }

    public List<Student> getAssignedStudentsByProfessor(String professorEmail) {
        String sql = "SELECT s.id, s.name, s.email " +
                     "FROM Student s " +
                     "JOIN Professor p ON s.professorId = p.id " +
                     "WHERE p.email = ? AND p.active = TRUE";
        return jdbcTemplate.query(sql, new Object[]{professorEmail}, new StudentRowMapper());
    }
}
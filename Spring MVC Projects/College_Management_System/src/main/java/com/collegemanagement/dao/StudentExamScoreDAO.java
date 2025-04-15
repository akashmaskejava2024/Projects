package com.collegemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.collegemanagement.entity.StudentExamScore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentExamScoreDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

 public void save(StudentExamScore score) {
    String sql = "INSERT INTO student_exam_score " +
            "(student_id, subject_id, internal_marks_obtained, internal_marks_total, external_marks_obtained, external_marks_total, active) " +
            "VALUES (?, ?, ?, ?, ?, ?, TRUE)";

jdbcTemplate.update(sql,
   score.getStudentId(),
   score.getSubjectId(),
   score.getInternalMarksObtained(),
   score.getInternalMarksTotal(),
   score.getExternalMarksObtained(),
   score.getExternalMarksTotal()
);
}
    public List<StudentExamScore> getScoresByStudentId(int studentId) {
        String sql = "SELECT * FROM student_exam_score WHERE student_id = ? AND active = TRUE";
        return jdbcTemplate.query(sql, new Object[]{studentId}, this::mapRowToStudentExamScore);
    }

    private StudentExamScore mapRowToStudentExamScore(ResultSet rs, int rowNum) throws SQLException {
        StudentExamScore score = new StudentExamScore();
        score.setId(rs.getInt("id"));
        score.setStudentId(rs.getInt("student_id"));
        score.setSubjectId(rs.getInt("subject_id"));
        score.setInternalMarksObtained(rs.getInt("internal_marks_obtained"));
        score.setInternalMarksTotal(rs.getInt("internal_marks_total"));
        score.setExternalMarksObtained(rs.getInt("external_marks_obtained"));
        score.setExternalMarksTotal(rs.getInt("external_marks_total"));
        score.setTotalMarksObtained(rs.getInt("total_marks_obtained"));
        score.setTotalMarksMax(rs.getInt("total_marks_max"));
        return score;
    }

    public int getSubjectCountForStudentInSemester(int studentId, int currentSemester) {
        String sql = "SELECT COUNT(DISTINCT subject_id) " +
                     "FROM student_exam_score ses " +
                     "JOIN subject s ON ses.subject_id = s.id " +
                     "WHERE ses.student_id = ? AND s.semester = ? AND ses.active = TRUE";
        return jdbcTemplate.queryForObject(sql, Integer.class, studentId, currentSemester);
    }

    public void deleteById(int id) {
        String sql = "UPDATE student_exam_score SET active = FALSE WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
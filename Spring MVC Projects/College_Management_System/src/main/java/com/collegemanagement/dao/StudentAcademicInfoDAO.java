package com.collegemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.collegemanagement.entity.StudentAcademicInfo;

@Repository
public class StudentAcademicInfoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert Student Academic Info
    public int save(StudentAcademicInfo info) {
        String sql = "INSERT INTO student_academic_info (student_id, academic_year, calendar_year, active) VALUES (?, ?, ?, true)";
        return jdbcTemplate.update(sql, info.getStudentId(), info.getAcademicYear(), info.getCalendarYear());
    }

    // Update student's academic year and calendar year
    public int updateAcademicInfo(int studentId, int newAcademicYear, String newCalendarYear) {
        String sql = "UPDATE student_academic_info SET academic_year = ?, calendar_year = ? WHERE student_id = ? AND active = true";
        return jdbcTemplate.update(sql, newAcademicYear, newCalendarYear, studentId);
    }

    // Get Student Academic Info by Student ID
    public StudentAcademicInfo getByStudentId(int studentId) {
        String sql = "SELECT * FROM student_academic_info WHERE student_id = ? AND active = true";
        return jdbcTemplate.queryForObject(sql, new StudentAcademicInfoRowMapper(), studentId);
    }

    // Get Students by Academic & Calendar Year
    public List<StudentAcademicInfo> getByAcademicYearAndCalendarYear(int academicYear, String calendarYear) {
        String sql = "SELECT * FROM student_academic_info WHERE academic_year = ? AND calendar_year = ? AND active = true";
        return jdbcTemplate.query(sql, new StudentAcademicInfoRowMapper(), academicYear, calendarYear);
    }

    // Update Student Academic Info
    public int update(StudentAcademicInfo info) {
        String sql = "UPDATE student_academic_info SET academic_year = ?, calendar_year = ? WHERE student_id = ? AND active = true";
        return jdbcTemplate.update(sql, info.getAcademicYear(), info.getCalendarYear(), info.getStudentId());
    }

    // Soft Delete (make inactive)
    public int delete(int studentId) {
        String sql = "UPDATE student_academic_info SET active = false WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }

    // RowMapper
    private static class StudentAcademicInfoRowMapper implements RowMapper<StudentAcademicInfo> {
        @Override
        public StudentAcademicInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentAcademicInfo info = new StudentAcademicInfo();
            info.setId(rs.getInt("id"));
            info.setStudentId(rs.getInt("student_id"));
            info.setAcademicYear(rs.getInt("academic_year"));
            info.setCalendarYear(rs.getString("calendar_year"));
            return info;
        }
    }

    public String generateCalendarYear(int academicYear) {
        int startYear = 2020 + academicYear - 1;
        return startYear + "-" + (startYear + 1);
    }

    public List<String> getDistinctCalendarYears() {
        String sql = "SELECT DISTINCT calendar_year FROM student_academic_info WHERE active = true ORDER BY calendar_year DESC";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<Integer> getDistinctAcademicYears() {
        String sql = "SELECT DISTINCT academic_year FROM student_academic_info WHERE active = true ORDER BY academic_year DESC";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    public List<String> getDistinctCourses() {
        String sql = "SELECT DISTINCT course FROM student_academic_info WHERE active = true ORDER BY course";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
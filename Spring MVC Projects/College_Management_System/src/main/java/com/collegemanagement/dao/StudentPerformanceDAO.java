package com.collegemanagement.dao;

import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.StudentCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StudentPerformanceDAO {

	private final JdbcTemplate jdbcTemplate;

	public StudentPerformanceDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> getMonthlyAttendanceSummary(int studentId) {
		String sql = "SELECT DATE_FORMAT(a.date, '%Y-%m') AS month, " +
		             "COUNT(a.id) AS studentPresentDays, " +
		             "25 AS totalWorkingDays " +  // Assuming 25 working days per month
		             "FROM Attendance a " +
		             "WHERE a.student_id = ? AND a.status = 'Present' AND a.active = TRUE " +
		             "GROUP BY month " +
		             "ORDER BY month DESC";

		return jdbcTemplate.queryForList(sql, studentId);
	}

	// Fetch Exam Scores
	public List<ExamScore> getExamScoresByStudentId(int studentId) {
		String sql = "SELECT * FROM exam_score WHERE student_id = ? AND active = TRUE";
		return jdbcTemplate.query(sql, new ExamScoreRowMapper(), studentId);
	}

	// Fetch Activities for a Student
	public List<Activity> getActivitiesByStudentId(int studentId) {
	    String sql = "SELECT id, student_id, type, subtype, competition_level, `rank`, achievement, image " +
	                 "FROM Activity WHERE student_id = ? AND active = TRUE";
	    return jdbcTemplate.query(sql, new ActivityRowMapper(), studentId);
	}

	// Fetch Course Information
	public List<Course> getCoursesByStudentId(int studentId) {
		String sql = "SELECT c.* FROM Course c " +
		             "INNER JOIN Student_Course sc ON c.id = sc.course_id " +
		             "WHERE sc.student_id = ? AND c.active = TRUE AND sc.active = TRUE";
		return jdbcTemplate.query(sql, new CourseRowMapper(), studentId);
	}
}
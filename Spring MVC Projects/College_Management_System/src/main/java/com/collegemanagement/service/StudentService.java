package com.collegemanagement.service;

import com.collegemanagement.dao.StudentDAO;
import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Student;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	private final StudentDAO studentDAO;

	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public int getTotalStudents() {
		return studentDAO.countStudents();
	}

	public void registerStudent(Student student) {
		studentDAO.registerStudent(student);
	}

	public Student loginStudent(String email, String password) {
		return studentDAO.login(email, password);
	}

	public Student getStudentById(int studentId) {
		return studentDAO.getStudentById(studentId);
	}

	public List<Course> getCoursesByStudentId(int studentId) {
		return studentDAO.getCoursesByStudentId(studentId);
	}

	public List<ExamScore> getScoresByStudentId(int studentId) {
		return studentDAO.getScoresByStudentId(studentId);
	}

	public List<Activity> getActivitiesByStudentId(int studentId) {
		return studentDAO.getActivitiesByStudentId(studentId);
	}

	public List<Attendance> getAttendanceByStudentId(int studentId) {
		return studentDAO.getAttendanceByStudentId(studentId);
	}

	public void updateStudentProfile(Student student) {
		studentDAO.updateStudentProfile(student);
	}

	@Transactional
	public void updateStudentAndProfessor(int studentId, int newProfessorId) {
		int updatedRows = studentDAO.updateStudentProfessor(studentId, newProfessorId);
		if (updatedRows == 0) {
			throw new RuntimeException("Failed to update professor for student with ID: " + studentId);
		}
	}

	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	public void deleteStudent(int id) {
		studentDAO.deleteStudent(id);
	}
}
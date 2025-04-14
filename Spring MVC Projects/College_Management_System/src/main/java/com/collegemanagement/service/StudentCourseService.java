package com.collegemanagement.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegemanagement.dao.CourseDAO;
import com.collegemanagement.dao.StudentCourseDAO;
import com.collegemanagement.dao.StudentDAO;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.Student;
import com.collegemanagement.entity.StudentCourse;

@Service
public class StudentCourseService {

	@Autowired
	private StudentCourseDAO studentCourseDAO;

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private CourseDAO courseDAO;

	// Assign a course to a student with semester
	public void assignCourse(int studentId, int courseId, int professorId, int semester) {
		studentCourseDAO.assignCourseToStudent(studentId, courseId, professorId, semester);
	}
	
	  @Transactional
	    public void updateStudentCourse(int studentId, int courseId, int newProfessorId, int newSemester) {
	        int updatedRows = studentCourseDAO.updateStudentCourse(studentId, courseId, newProfessorId, newSemester);
	        if (updatedRows == 0) {
	            throw new RuntimeException("Failed to update StudentCourse for student ID: " + studentId);
	        }
	    }

	public List<StudentCourse> getCoursesByProfessorId(int professorId) {
		return studentCourseDAO.getCoursesByProfessorId(professorId);
	}

	public StudentCourse getCourseByProfessorId(int professorId) {
		return studentCourseDAO.findCourseByProfessorId(professorId);
	}

	// Fetch courses for a single student in a given semester
	public List<Course> getCoursesForStudent(int studentId, int semester) {
		return studentCourseDAO.getCoursesAssignedToStudent(studentId, semester);
	}

	public List<Course> getCoursesForStudent(int studentId) {
		return studentCourseDAO.getCoursesAssignedToStudent(studentId);
	}

	// Fetch all students from the database
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}

	// Fetch all courses from the database
	public List<Course> getAllCourses() {
		return courseDAO.getAllCourses();
	}

	// Fetch courses for multiple students efficiently
	public Map<Integer, List<Course>> getCoursesForStudents(List<Student> students, int semester) {
		if (students.isEmpty()) {
			return Collections.emptyMap();
		}

		List<Integer> studentIds = students.stream().map(Student::getId).collect(Collectors.toList());
		return studentCourseDAO.getCoursesForStudents(studentIds, semester);
	}

	// Fetch courses for multiple students efficiently
	public Map<Integer, List<Course>> getCoursesForStudents(List<Student> students) {
		if (students.isEmpty()) {
			return Collections.emptyMap();
		}

		List<Integer> studentIds = students.stream().map(Student::getId).collect(Collectors.toList());
		return studentCourseDAO.getStudentsWithCourses(studentIds);
	}

	// Fetch student course by studentId and semester
	public StudentCourse getStudentCourseByStudentId(int studentId, int semester) {
		return studentCourseDAO.getStudentCourseByStudentId(studentId, semester);
	}

	public Integer getSemesterByStudentAndProfessor(int id, Integer professorId) {
		
		return studentCourseDAO.getSemesterByStudentAndProfessor(id, professorId);
	}
}
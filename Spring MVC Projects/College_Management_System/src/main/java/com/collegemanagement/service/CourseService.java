package com.collegemanagement.service;

import com.collegemanagement.dao.CourseDAO;
import com.collegemanagement.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }

    // Fetch a course by ID
    public Course getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }
    
    public int getTotalCourses() {
        return courseDAO.countCourses();
    }

	public List<Integer> getSemestersByCourseId(int courseId) {
		
		return courseDAO.getSemestersByCourseId(courseId);
	}
}
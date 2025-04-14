package com.collegemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.collegemanagement.dao.SubjectDAO;
import com.collegemanagement.entity.Subject;

@Service
public class SubjectService {
	private final SubjectDAO subjectDAO;

	public SubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public List<Subject> getSubjectsByCourseAndSemester(int courseId, int semester) {
		return subjectDAO.findByCourseIdAndSemester(courseId, semester);
	}

	public List<Subject> getSubjectsByCourse(int courseId) {
		return subjectDAO.getSubjectsByCourse(courseId);
	}

	public List<Subject> getSubjectsBySemester(int semester) {
		return subjectDAO.getSubjectsBySemester(semester);
	}

	public void addSubject(Subject subject) {
		subjectDAO.addSubject(subject);
	}

	public void deleteSubject(int id) {
		subjectDAO.deleteSubject(id);
	}

	public List<Subject> getSubjectsByStudentId(int studentId) {
		return subjectDAO.getSubjectsByStudentId(studentId);
	}
	
	

    // Fetch subject by ID
    public Subject getSubjectById(int subjectId) {
        return subjectDAO.getSubjectById(subjectId);
    }

	public int getTotalSubjectsForSemester(int courseId, int currentSemester) {
	    
	  List<Subject> subjects= 	subjectDAO.findByCourseIdAndSemester(courseId, currentSemester);
	  
	  int subjectCount = 0;
	  for(Subject s: subjects) {
		  subjectCount += 1;
	  }
	  
	  return subjectCount;
	}
}
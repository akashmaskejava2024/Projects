package com.rt.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.StudentEntity.StudentEntity;
import com.rt.dao.LibraryDao;

@Service
public class LibraryService {
 @Autowired
 LibraryDao librarydao;
 
	public boolean addStudent(StudentEntity student) {
      return librarydao.addStudent(student);
	}

	public List<StudentEntity> all() {
		return librarydao.AllStudents();
	}

	public boolean UpdateStudent(StudentEntity update) {
		return librarydao.StudentUpdate(update);
	}

	public StudentEntity getStudentById(int StudentId) {
		return librarydao.getStudentById(StudentId);
	}

	public boolean update(StudentEntity student) {
		return librarydao.update(student);	}

	public boolean delete(int studentId) {
		return librarydao.delete(studentId);

	}

	public int getnumberOfStudents() {
		   return librarydao.getNumberOfStudents();

	}

	

	


}
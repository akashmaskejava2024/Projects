package com.rt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.dao.TeacherDao;
import com.rt.entity.TeacherEntity;

@Service
public class TeacherService {
@Autowired
	TeacherDao	teacherDao;
	public boolean add(TeacherEntity teacher) {
		
		return teacherDao.saveTeacher(teacher);
	}
	
	public List<TeacherEntity> all() {
		return teacherDao.AllTeachers();
	}

	public boolean update(com.rt.entity.TeacherEntity teacher) {
		return teacherDao.update(teacher);
	}

	public boolean delete(int teacherId) {
		return teacherDao.delete(teacherId);
	}

	public int getnumberOfTeachers() {
		 return teacherDao.getnumberOfTeachers();
	}

	public TeacherEntity getTeacherById(int teacherId) {
		return teacherDao.getTeacherById(teacherId);
	}




}
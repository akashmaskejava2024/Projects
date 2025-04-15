package com.rt.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.StudentEntity.StudentEntity;
import com.rt.mapper.StudentRowMapper;

@Repository

public class LibraryDao {
	@Autowired
	JdbcTemplate template;

	public boolean addStudent(StudentEntity student) {
	    try {
	        Object args[] = { student.getFirstName(), student.getLastName(), student.getEmail(),student.getClassName(),student.getDepartment(),
	                student.getContactNumber()};
	        int data = template.update(
	                "insert into student(`FirstName`,`LastName`,`Email`,`className`,`department`,`ContactNumber`) values(?,?,?,?,?,?)",
	                args);
	        if (data == 1) {
	            return true;
	        } else {
	            throw new RuntimeException("Failed to insert student record. No rows affected.");
	        }

	    } catch (Exception e) {
	        throw new RuntimeException("Error while inserting student record", e);
	    }
	}


	
	public List<StudentEntity> AllStudents() {
		List<StudentEntity> list = null;
		try {
			Object[] args = {};
			list = template.query("select * from student", args, new StudentRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean StudentUpdate(StudentEntity update) {

		return false;
	}
	

	public StudentEntity getStudentById(int StudentId) {
		StudentEntity studentdata = null;
		try {
			Object[] args = { StudentId };
			studentdata = template.queryForObject("select * from student where StudentId=? ", args, new StudentRowMapper());

		} catch (DataAccessException e) {
			// Handle the case where no student was found
			System.out.println("No Student found with StudentID: " + StudentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(studentdata);
		return studentdata;
	}
	
	

	public boolean update(StudentEntity student) {
		try {
			Object args[] = {student.getFirstName(), student.getLastName(), student.getEmail(),student.getClassName(),student.getDepartment(),
					student.getContactNumber(),student.getStudentId()};
			 int studentList = template.update( "update student SET `FirstName` = ?, `LastName` = ?, `Email` = ?, `ClassName` = ?, `Department` = ?, "
		        		+ "`ContactNumber` = ? WHERE studentId = ?", args);
			 
			 if (studentList == 1) {
		            return true;
		        } else {
		            return false;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }	
          }


	public boolean delete(int studentId) {
		try {

			Object[] args = {studentId};
			int data = template.update("delete from student where studentId=?", args);
			 
			 if(data==1){
				 return true;
			 }	

		} catch (Exception e) {
         System.out.println(e);
		}
		return false;
	}



	public int getNumberOfStudents() {
		return template.queryForObject("SELECT COUNT(*) FROM student", Integer.class);
	}

	
}
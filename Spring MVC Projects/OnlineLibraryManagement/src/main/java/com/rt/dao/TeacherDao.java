package com.rt.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.entity.TeacherEntity;
import com.rt.mapper.TeacherRowMapper;
@Repository
public class TeacherDao {
	@Autowired
	JdbcTemplate Template;
//Register teacher operation
	public boolean saveTeacher(TeacherEntity teacher) {
		try{
			Object[] args={teacher.getFirstName(),teacher.getLastName(),teacher.getEmail(),teacher.getContactNumber(),teacher.getFaculty()};
			int ADDteacher=Template.update("insert into teacher(`FirstName`,`LastName`,`Email`,`ContactNumber`,`faculty`)values(?,?,?,?,?)",
					args);
		
				if(ADDteacher==1)
				{
					return true;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return false;
			
			}
	
	
	//SELECT ALL TEACHER OPERATION
	public List<TeacherEntity> AllTeachers() {
		List<TeacherEntity> list = null;
		try {
			Object[] args = {};
			list = Template.query("select * from teacher", args,new TeacherRowMapper());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

/*

public boolean update(Teacher teacher) {
		 try {
		        Object[] args = {teacher.getFirstName(), teacher.getLastName(),
		        		teacher.getEmail(), teacher.getContactNumber(), teacher.getFaculty(),teacher.getTeacherId()};
		        
		        String sql = "update teacher SET `FirstName` = ?, `LastName` = ?, `Email` = ?, `ContactNumber` = ?, `faculty` = ?,  WHERE TeacherId = ?";
		        System.out.println("SQL statement: " + sql);
		        
		        int a = Template.update(sql, args);

		        if (a == 1) {
		            return true;
		        } else {
		            return false;
		        }
		    } catch (Exception e2) {
		        e2.printStackTrace();
		        return false;
		    }
		}

*/

	public boolean update(TeacherEntity teacher) {
	    try {
	        Object[] args = {teacher.getFirstName(), teacher.getLastName(),
	                teacher.getEmail(), teacher.getContactNumber(), teacher.getFaculty(), teacher.getTeacherId()};
	       
	        String sql = "UPDATE teacher SET FirstName = ?, LastName = ?, Email = ?, ContactNumber = ?, faculty = ? WHERE TeacherId = ?";
	        System.out.println("SQL statement: " + sql);
	        
	        int rowsAffected = Template.update(sql, args);
	        
	        return rowsAffected == 1;
	    } catch (Exception e) {
	      	   e.printStackTrace();
	        return false; 
	    }
	}



public boolean delete(int teacherId) {
		try {

			Object[] args = {teacherId};
			int data = Template.update("delete from teacher where TeacherId=?", args);
			 
			 if(data==1){
				 return true;
			 }	

		} catch (Exception e2) {
         System.out.println(e2);
		}
		return false;
	}


public int getnumberOfTeachers() {
	return Template.queryForObject("SELECT COUNT(*) FROM teacher", Integer.class);}


public TeacherEntity getTeacherById(int teacherId) {
    TeacherEntity teacherData = null;
    try {
        Object[] args = { teacherId };
        teacherData = Template.queryForObject("SELECT * FROM teacher WHERE TeacherId=?", args, new TeacherRowMapper());
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(teacherData);
    return teacherData;
}


	}

	


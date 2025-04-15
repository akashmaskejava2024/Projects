package com.rt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.StudentEntity.StudentEntity;



public class StudentRowMapper implements RowMapper<StudentEntity> {

	public StudentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new StudentEntity(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
	}
}



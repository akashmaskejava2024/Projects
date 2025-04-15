package com.rt.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.entity.TeacherEntity;



public class TeacherRowMapper implements RowMapper<TeacherEntity> {
	public TeacherEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new TeacherEntity(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
	}





}

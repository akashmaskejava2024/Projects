package com.rt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.entity.IssueBookEntity;



public class issuerecordRowMapper implements RowMapper<IssueBookEntity> {
	
	public IssueBookEntity mapRow(ResultSet bk, int rowNum) throws SQLException {
		return new IssueBookEntity (bk.getInt(1),bk.getInt(2),bk.getString(3),bk.getInt(4),bk.getString(5),bk.getString(6),bk.getString(7));
	}
}
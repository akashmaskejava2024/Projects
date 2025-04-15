package com.rt.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rt.entity.BookEntity;

public class BookRowMapper implements RowMapper<BookEntity> {

	public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new BookEntity (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11));
	}
	}
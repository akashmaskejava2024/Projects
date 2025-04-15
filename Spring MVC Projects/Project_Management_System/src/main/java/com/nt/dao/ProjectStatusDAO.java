package com.nt.dao;

import com.nt.entity.ProjectStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectStatusDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean saveProjectStatus(ProjectStatus projectStatus) {

			// If not exists, insert a new record
			String insertSql = "INSERT INTO ProjectStatus (projectId, studentId, phase, uiImage, updateDescription, feedback, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
			int result = jdbcTemplate.update(insertSql, projectStatus.getProjectId(), projectStatus.getStudentId(),
					projectStatus.getPhase(), projectStatus.getUiImage(), projectStatus.getUpdateDescription(),
					projectStatus.getFeedback(), projectStatus.getUpdatedAt());
			return result > 0;
	}

	public ProjectStatus getProjectStatusByStudentId(Long studentId) {
		String sql = "SELECT * FROM ProjectStatus WHERE studentId = ?";

		try {
			return jdbcTemplate.queryForObject(sql, new ProjectStatusRowMapper(), studentId);
		} catch (EmptyResultDataAccessException e) {
			return null; // Return null if no project status is found
		}
	}
	

    public List<ProjectStatus> getProjectStatusesByStudentId(Long studentId) {
        String sql = "SELECT * FROM ProjectStatus WHERE studentId = ? ORDER BY updatedAt DESC";
        return jdbcTemplate.query(sql, new ProjectStatusRowMapper(), studentId);
    }

	private static class ProjectStatusRowMapper implements RowMapper<ProjectStatus> {
		@Override
		public ProjectStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectStatus projectStatus = new ProjectStatus();
			projectStatus.setId(rs.getLong("id"));
			projectStatus.setProjectId(rs.getLong("projectId"));
			projectStatus.setStudentId(rs.getLong("studentId"));
			projectStatus.setPhase(rs.getString("phase"));
			projectStatus.setUiImage(rs.getString("uiImage"));
			projectStatus.setUpdateDescription(rs.getString("updateDescription"));
			projectStatus.setFeedback(rs.getString("feedback"));
			projectStatus.setUpdatedAt(rs.getTimestamp("updatedAt"));
			return projectStatus;
		}
	}

	public List<ProjectStatus> getProjectStatusesByProfessor(Long professorId) {
		String sql = "SELECT ps.* FROM ProjectStatus ps " + "JOIN Student s ON ps.studentId = s.id "
				+ "WHERE s.professorId = ?";

		return jdbcTemplate.query(sql, new ProjectStatusRowMapper(), professorId);
	}

	// ✅ Add Feedback to a project status
	public void addFeedback(Long projectStatusId, String feedback) {
		String sql = "UPDATE ProjectStatus SET feedback = ? WHERE id = ?";
		jdbcTemplate.update(sql, feedback, projectStatusId);
	}
}
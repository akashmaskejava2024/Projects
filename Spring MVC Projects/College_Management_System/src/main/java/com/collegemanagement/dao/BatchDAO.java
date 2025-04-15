package com.collegemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.collegemanagement.entity.Batch;

@Repository
public class BatchDAO {
	private final JdbcTemplate jdbcTemplate;

	public BatchDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Map<Integer, List<Integer>> getUnassignedSemestersByCourse() {
	    String totalSemestersQuery = "SELECT id, semester FROM course WHERE active = true";
	    String assignedSemestersQuery = "SELECT course_id, semester FROM batch WHERE active = true";

	    Map<Integer, Integer> courseToTotalSemesters = jdbcTemplate.query(totalSemestersQuery, rs -> {
	        Map<Integer, Integer> map = new HashMap<>();
	        while (rs.next()) {
	            map.put(rs.getInt("id"), rs.getInt("semester"));
	        }
	        return map;
	    });

	    Map<Integer, Set<Integer>> courseToAssignedSemesters = jdbcTemplate.query(assignedSemestersQuery, rs -> {
	        Map<Integer, Set<Integer>> map = new HashMap<>();
	        while (rs.next()) {
	            int courseId = rs.getInt("course_id");
	            int semester = rs.getInt("semester");
	            map.computeIfAbsent(courseId, k -> new HashSet<>()).add(semester);
	        }
	        return map;
	    });

	    Map<Integer, List<Integer>> courseSemesterMap = new HashMap<>();

	    for (Map.Entry<Integer, Integer> entry : courseToTotalSemesters.entrySet()) {
	        int courseId = entry.getKey();
	        int totalSemesters = entry.getValue();
	        Set<Integer> assignedSemesters = courseToAssignedSemesters.getOrDefault(courseId, new HashSet<>());
	        List<Integer> unassigned = new ArrayList<>();
	        for (int i = 1; i <= totalSemesters; i++) {
	            if (!assignedSemesters.contains(i)) {
	                unassigned.add(i);
	            }
	        }
	        if (!unassigned.isEmpty()) {
	            courseSemesterMap.put(courseId, unassigned);
	        }
	    }

	    return courseSemesterMap;
	}

	public List<Batch> getBatchesByProfessorId(int professorId) {
		String sql = "SELECT * FROM batch WHERE professor_id = ? AND active = true";
		return jdbcTemplate.query(sql, new BatchRowMapper(), professorId);
	}

	public boolean assignBatchToProfessor(int professorId, int courseId, int semester) {
		String checkSql = "SELECT COUNT(*) FROM batch WHERE course_id = ? AND semester = ? AND professor_id IS NOT NULL AND active = true";
		int count = jdbcTemplate.queryForObject(checkSql, Integer.class, courseId, semester);
		if (count > 0) return false;

		String sql = "INSERT INTO batch (professor_id, course_id, semester, active) VALUES (?, ?, ?, true)";
		return jdbcTemplate.update(sql, professorId, courseId, semester) > 0;
	}

	public boolean isBatchAssigned(int professorId, int courseId, int semester) {
		String sql = "SELECT COUNT(*) FROM batch WHERE professor_id = ? AND course_id = ? AND semester = ? AND active = true";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, professorId, courseId, semester);
		return count != null && count > 0;
	}

	public List<Batch> getAllBatches() {
		String sql = "SELECT * FROM batch WHERE active = true";
		return jdbcTemplate.query(sql, new BatchRowMapper());
	}

	public int updateProfessorForBatch(int currentProfessorId, int newProfessorId, int courseId, int semester) {
		String sql = "UPDATE batch SET professor_id = ? WHERE professor_id = ? AND course_id = ? AND semester = ? AND active = true";
		return jdbcTemplate.update(sql, newProfessorId, currentProfessorId, courseId, semester);
	}

	public Batch getBatchByCourseAndSemester(int courseId, int newSemester) {
	    String sql = "SELECT * FROM batch WHERE course_id = ? AND semester = ? AND active = 1";

	    System.out.println("🔍 Looking for batch with courseId = " + courseId + ", semester = " + newSemester);

	    try {
	        Batch batch = jdbcTemplate.queryForObject(
	            sql,
	            new Object[]{courseId, newSemester},
	            new BatchRowMapper()
	        );

	        System.out.println("✅ Batch found: " + batch.getId()); // assuming getId() exists
	        return batch;

	    } catch (EmptyResultDataAccessException e) {
	        System.out.println("❌ No batch found for courseId=" + courseId + ", semester=" + newSemester);
	        throw new RuntimeException("Current batch not found for courseId " + courseId + " and semester " + newSemester);
	    }
	}

	public Map<Integer, List<Integer>> getAssignedSemestersByCourse() {
		String sql = "SELECT course_id, semester FROM batch WHERE active = true";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		Map<Integer, List<Integer>> result = new HashMap<>();
		for (Map<String, Object> row : rows) {
			Integer courseId = (Integer) row.get("course_id");
			Integer semester = (Integer) row.get("semester");
			result.computeIfAbsent(courseId, k -> new ArrayList<>()).add(semester);
		}
		return result;
	}

	public boolean softDeleteBatch(int batchId) {
		String sql = "UPDATE batch SET active = false WHERE id = ?";
		return jdbcTemplate.update(sql, batchId) > 0;
	}

	public Batch getBatchById(int batchId) {
	    String sql = "SELECT * FROM batch WHERE id = ? AND active = TRUE";
	    try {
	        return jdbcTemplate.queryForObject(sql, new Object[]{batchId}, new BatchRowMapper());
	    } catch (EmptyResultDataAccessException e) {
	        return null; // No active batch found
	    }
	}
}

class BatchRowMapper implements RowMapper<Batch> {
	@Override
	public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Batch(rs.getInt("id"), rs.getInt("course_id"), rs.getInt("semester"), rs.getInt("professor_id"));
	}
}
package com.collegemanagement.dao;

import com.collegemanagement.entity.Activity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAO {

    private final JdbcTemplate jdbcTemplate;

    public ActivityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addActivity(Activity activity) {
        String sql = "INSERT INTO activity (student_id, type, subtype, competition_level, `rank`, achievement, image, active) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, TRUE)";
        return jdbcTemplate.update(sql,
                activity.getStudentId(),
                activity.getType(),
                activity.getSubtype(),
                activity.getCompetitionLevel(),
                activity.getRank(),
                activity.getAchievement(),
                activity.getImage()
        );
    }

    public List<Activity> getActivitiesByStudentId(int studentId) {
        String sql = "SELECT * FROM activity WHERE student_id = ? AND active = TRUE";
        return jdbcTemplate.query(sql, new ActivityRowMapper(), studentId);
    }

    // Soft delete: Mark as inactive
    public int deactivateActivity(int activityId) {
        String sql = "UPDATE activity SET active = FALSE WHERE id = ?";
        return jdbcTemplate.update(sql, activityId);
    }
}
package com.collegemanagement.service;

import com.collegemanagement.dao.ActivityDAO;
import com.collegemanagement.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    public void addActivity(Activity activity) {
        activityDAO.addActivity(activity);
    }

    public List<Activity> getActivitiesByStudent(int studentId) {
        return activityDAO.getActivitiesByStudentId(studentId);
    }

}
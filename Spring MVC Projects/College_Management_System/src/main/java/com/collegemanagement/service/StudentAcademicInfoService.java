package com.collegemanagement.service;

import com.collegemanagement.dao.StudentAcademicInfoDAO;
import com.collegemanagement.entity.StudentAcademicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentAcademicInfoService {

    @Autowired
    private StudentAcademicInfoDAO studentAcademicInfoDAO;

    // Save or update Student Academic Info
    public void saveOrUpdate(StudentAcademicInfo info) {
        Optional<StudentAcademicInfo> existingInfo = getByStudentId(info.getStudentId());
        if (existingInfo.isPresent()) {
            studentAcademicInfoDAO.update(info);
        } else {
            studentAcademicInfoDAO.save(info);
        }
    }

    @Transactional
    public void updateAcademicInfo(int studentId, int newAcademicYear, String newCalendarYear) {
        int updatedRows = studentAcademicInfoDAO.updateAcademicInfo(studentId, newAcademicYear, newCalendarYear);
        if (updatedRows == 0) {
            throw new RuntimeException("Failed to update StudentAcademicInfo for student ID: " + studentId);
        }
    }
    
    // Get Student Academic Info by Student ID
    public Optional<StudentAcademicInfo> getByStudentId(int studentId) {
        try {
            return Optional.ofNullable(studentAcademicInfoDAO.getByStudentId(studentId));
        } catch (Exception e) {
            return Optional.empty(); // Handles cases where no record is found
        }
    }

    // Delete Student Academic Info
    public void deleteByStudentId(int studentId) {
        studentAcademicInfoDAO.delete(studentId);
    }
}
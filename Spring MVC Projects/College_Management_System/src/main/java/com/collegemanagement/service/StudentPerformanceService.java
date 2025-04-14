package com.collegemanagement.service;

import com.collegemanagement.dao.StudentExamScoreDAO;
import com.collegemanagement.dao.StudentPerformanceDAO;
import com.collegemanagement.dao.SubjectDAO;
import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.StudentExamScore;
import com.collegemanagement.entity.Subject;
import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentPerformanceService {
    
    private final StudentPerformanceDAO studentPerformanceDAO;

    public StudentPerformanceService(StudentPerformanceDAO studentPerformanceDAO) {
        this.studentPerformanceDAO = studentPerformanceDAO;
    }
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private StudentExamScoreDAO studentExamScoreDAO;

    public List<Map<String, Object>> getMonthlyAttendance(int studentId) {
        return studentPerformanceDAO.getMonthlyAttendanceSummary(studentId);
    }

    public List<ExamScore> getExamScores(int studentId) {
        return studentPerformanceDAO.getExamScoresByStudentId(studentId);
    }

    public List<Activity> getActivities(int studentId) {
        return studentPerformanceDAO.getActivitiesByStudentId(studentId);
    }

    public List<Course> getCourses(int studentId) {
        return studentPerformanceDAO.getCoursesByStudentId(studentId);
    }
    
    public List<Map<String, Object>> getExamScoresWithCourseName(int studentId) {
        List<ExamScore> examScores = studentPerformanceDAO.getExamScoresByStudentId(studentId);
        List<Map<String, Object>> examScoresWithCourseName = new ArrayList<>();

        for (ExamScore examScore : examScores) {
            Course course = courseService.getCourseById(examScore.getCourseId()); // Fetch course details

            Map<String, Object> examScoreMap = new HashMap<>();
            examScoreMap.put("courseName", course.getName());  // Add course name
            examScoreMap.put("semester", examScore.getSemester());
            examScoreMap.put("marks", examScore.getMarks());
            examScoreMap.put("totalMarks", examScore.getTotalMarks());

            examScoresWithCourseName.add(examScoreMap);
        }
        return examScoresWithCourseName;
    }
    public List<Map<String, Object>> getSubjectWiseExamScores(int studentId) {
        List<StudentExamScore> examScores = studentExamScoreDAO.getScoresByStudentId(studentId);
        List<Map<String, Object>> subjectWiseScores = new ArrayList<>();

        for (StudentExamScore examScore : examScores) {
            Subject subject = subjectService.getSubjectById(examScore.getSubjectId());
            if (subject != null) {
                Map<String, Object> scoreMap = new HashMap<>();
                scoreMap.put("semesterNo", subject.getSemester()); // Fetch semester number
                scoreMap.put("subjectName", subject.getName());
                scoreMap.put("internalMarks", examScore.getInternalMarksObtained());
                scoreMap.put("internalMarksTotal", examScore.getInternalMarksTotal());
                scoreMap.put("externalMarks", examScore.getExternalMarksObtained());
                scoreMap.put("externalMarksTotal", examScore.getExternalMarksTotal());
                scoreMap.put("totalMarksObtained", examScore.getTotalMarksObtained());
                scoreMap.put("totalMarksMax", examScore.getTotalMarksMax());
                subjectWiseScores.add(scoreMap);
            }
        }
        return subjectWiseScores;
    }
  
    
}
package com.collegemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegemanagement.dao.StudentExamScoreDAO;
import com.collegemanagement.entity.StudentExamScore;

import java.util.List;

@Service
public class StudentExamScoreService {

    @Autowired
    private StudentExamScoreDAO studentExamScoreDAO;

    @Transactional
    public void addExamScores(List<StudentExamScore> scores) {
        for (StudentExamScore score : scores) {
            studentExamScoreDAO.save(score);
        }
    }

    public List<StudentExamScore> getScoresByStudentId(int studentId) {
        return studentExamScoreDAO.getScoresByStudentId(studentId);
    }

	public int getSubjectCountForStudentInSemester(int studentId, int currentSemester) {
		
	return 	studentExamScoreDAO.getSubjectCountForStudentInSemester(studentId, currentSemester);
		
		
	}
}
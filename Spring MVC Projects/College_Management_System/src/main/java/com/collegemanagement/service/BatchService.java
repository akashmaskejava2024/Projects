package com.collegemanagement.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collegemanagement.dao.BatchDAO;
import com.collegemanagement.entity.Batch;

@Service
public class BatchService {
    private final BatchDAO batchDAO;

    public BatchService(BatchDAO batchDAO) {
        this.batchDAO = batchDAO;
    }
    
 // BatchService.java
    public Map<Integer, List<Integer>> getUnassignedSemestersGroupedByCourse() {
        return batchDAO.getUnassignedSemestersByCourse();
    }

    public List<Batch> getAllBatches() {
        return batchDAO.getAllBatches();
    }
    
    public List<Batch> getBatchesByProfessorId(int professorId) {
        return batchDAO.getBatchesByProfessorId(professorId);
    }
    @Transactional
    public boolean assignBatchToProfessor(int professorId, int courseId, int semester) {
        return batchDAO.assignBatchToProfessor(professorId, courseId, semester);
    }

	public Batch getBatchByCourseAndSemester(int courseId, int newSemester) {
		
		return batchDAO.getBatchByCourseAndSemester(courseId, newSemester);
	}

	public Batch getBatchById(int batchId) {
		
		return batchDAO.getBatchById(batchId);
	}


}
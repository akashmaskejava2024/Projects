package com.collegemanagement.service;

import com.collegemanagement.dao.BatchDAO;
import com.collegemanagement.dao.ProfessorDAO;
import com.collegemanagement.dao.StudentCourseDAO;
import com.collegemanagement.dao.StudentDAO;
import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Professor;
import com.collegemanagement.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorDAO professorDAO;
    
    @Autowired
    private StudentCourseDAO studentCourseDAO;

    @Autowired
    private StudentDAO studentDAO;
    
    @Autowired
    private BatchDAO batchDAO;

    public int getTotalProfessors() {
        return professorDAO.countProfessors();
    }

    
    // Register a new professor (Signup)
    public String registerProfessor(Professor professor) {
        if (professorDAO.existsByEmail(professor.getEmail())) {
            return "Email already exists! Try another email.";
        }
        int result = professorDAO.addProfessor(professor);
        return result > 0 ? "Professor registered successfully!" : "Error registering professor.";
    }

    // Get all professors
    public List<Professor> getAllProfessors() {
        return professorDAO.getAllProfessors();
    }

    // Get professor by ID
    public Optional<Professor> getProfessorById(int id) {
        return professorDAO.getProfessorById(id);
    }

    // Update professor details
    public String updateProfessor(Professor professor) {
        int result = professorDAO.updateProfessor(professor);
        return result > 0 ? "Professor updated successfully!" : "Error updating professor.";
    }

    // Delete professor
    public String deleteProfessor(int id) {
        int result = professorDAO.deleteProfessor(id);
        return result > 0 ? "Professor deleted successfully!" : "Error deleting professor.";
    }

    // Authenticate professor login
    public Optional<Professor> loginProfessor(String email, String password) {
        return professorDAO.loginProfessor(email, password);
    }
    
    

    // ✅ Fetch assigned students
    public List<Student> getAssignedStudents(int professorId) {
        return professorDAO.findStudentsByProfessorId(professorId);
    }

    // ✅ Add exam score
    public void addExamScore(ExamScore examScore) {
        professorDAO.insertExamScore(examScore);
    }
    
    
    public void markAttendanceForAll(String date, List<Integer> presentStudents, int professorId) {
        List<Student> students = getAssignedStudents(professorId);

        for (Student student : students) {
            String status = (presentStudents != null && presentStudents.contains(student.getId())) ? "Present" : "Absent";
            professorDAO.markAttendance(student.getId(), date, status);
        }
    }

	public List<Course> getAssignedCourses(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Transactional
	public String updateProfessorForBatch(int currentProfessorId, int newProfessorId, int courseId, int semester) {
	    // Update professor in student_course table
	    int updatedStudentCourseCount = studentCourseDAO.updateProfessorForCourse(currentProfessorId, newProfessorId, courseId, semester);

	    // Update professor in student table
	    int updatedStudentCount = studentDAO.updateProfessorForStudents(currentProfessorId, newProfessorId, courseId, semester);
	    
	    // Update professor in batch table
	    int updatedBatchCount = batchDAO.updateProfessorForBatch(currentProfessorId, newProfessorId, courseId, semester);

	    return updatedStudentCourseCount + " students' course records, " +
	           updatedStudentCount + " student profiles, and " +
	           updatedBatchCount + " batch records updated successfully!";
	}
}
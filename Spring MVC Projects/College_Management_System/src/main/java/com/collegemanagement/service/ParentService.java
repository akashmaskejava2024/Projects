package com.collegemanagement.service;

import com.collegemanagement.dao.ParentDAO;
import com.collegemanagement.entity.Parent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    private final ParentDAO parentDAO;

    public ParentService(ParentDAO parentDAO) {
        this.parentDAO = parentDAO;
    }

    public int addParent(Parent parent) {
        return parentDAO.addParent(parent);
    }

    public Parent getParentById(int id) {
        return parentDAO.getParentById(id);
    }
    
    public Parent getParentByEmail(String email) {
        return parentDAO.findByEmail(email);
    }

    public Parent getParentByStudentId(int studentId) {
        return parentDAO.getParentByStudentId(studentId);
    }

    public List<Parent> getAllParents() {
        return parentDAO.getAllParents();
    }

    public int updateParent(Parent parent) {
        return parentDAO.updateParent(parent);
    }

    public int deleteParent(int id) {
        return parentDAO.deleteParent(id);
    }
}

package com.nt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.dao.ProjectDAO;
import com.nt.entity.Project;
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectDAO projectDAO;

    @GetMapping("/new")
    public String showProjectForm(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/student/login"; // Redirect to login if not logged in
        }
        model.addAttribute("project", new Project());
        return "jsp/student/newProject"; // Loads newProject.jsp
    }

    @PostMapping("/save")
    public String saveProject(@ModelAttribute Project project, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }
        project.setStudentId(studentId);
        projectDAO.saveProject(project);
        return "redirect:/student/dashboard"; // Redirect to dashboard after adding project
    }
}
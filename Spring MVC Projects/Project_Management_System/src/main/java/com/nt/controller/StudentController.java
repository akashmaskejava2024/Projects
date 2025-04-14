package com.nt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.nt.dao.ProjectDAO;
import com.nt.dao.ProjectStatusDAO;
import com.nt.dao.StudentDAO;
import com.nt.dto.ProjectStatusDTO;
import com.nt.entity.Project;
import com.nt.entity.ProjectStatus;
import com.nt.entity.Student;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    @Autowired
    private ProjectDAO projectDAO;
    
    @Autowired
    private ProjectStatusDAO projectStatusDAO;

    // Show signup page
    @GetMapping("/signup")
    public String showSignupForm() {
        return "student/signup"; // Path: WEB-INF/jsp/student/signup.jsp
    }
    

    // Show update project status page
    @GetMapping("/updateProjectStatus")
    public String showUpdateProjectStatusPage(@RequestParam("projectId") Long projectId, Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }
        model.addAttribute("projectId", projectId);
        return "student/updateProjectStatus"; // Load updateProjectStatus.jsp
    }

    // Process signup form
    @PostMapping("/signup")
    public String registerStudent(@ModelAttribute Student student, Model model) {
        int result = studentDAO.registerStudent(student);
        if (result > 0) {
            model.addAttribute("message", "Signup successful! Please login.");
            return "student/login";
        } else {
            model.addAttribute("message", "Signup failed. Try again.");
            return "student/signup";
        }
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "student/login"; // Path: WEB-INF/jsp/student/login.jsp
    }
    

    @GetMapping("/newProject")
    public String showNewProjectPage(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }
        return "student/newProject"; // Loads newProject.jsp
    }

    // Process login form
    @PostMapping("/login")
    public String loginStudent(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Student student = studentDAO.loginStudent(email, password);
        if (student != null) {
            session.setAttribute("student", student);
            session.setAttribute("studentId", student.getId());

            return "redirect:/student/dashboard";
        } else {
            model.addAttribute("message", "Invalid email or password");
            return "student/login";
        }
    }

    @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        // Fetch project details for the logged-in student
        Project project = projectDAO.getProjectByStudentId(student.getId());
        model.addAttribute("project", project); // Pass project data to the JSP

        // Fetch all project statuses for the project
        if (project != null) {
            List<ProjectStatus> projectStatuses = projectStatusDAO.getProjectStatusesByStudentId(student.getId());
            model.addAttribute("projectStatuses", projectStatuses);
        }


        model.addAttribute("student", student);
        return "student/dashboard"; // Path: WEB-INF/jsp/student/dashboard.jsp
    }
    
    
    
    
    
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
    
    
    
    
    @PostMapping("/updateProjectStatus")
    public String updateProjectStatus(
            @ModelAttribute ProjectStatusDTO projectStatusDTO,
            HttpSession session) {

        // ✅ Validate session for student login
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) {
            return "redirect:/student/login";
        }

        MultipartFile uiImage = projectStatusDTO.getUiImage();
        String imagePath = null;

        try {
            // ✅ Define absolute upload path (change username accordingly)
            String uploadDir = "/Users/akashabamaske/uploads/";
            File uploadFolder = new File(uploadDir);

            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // Ensure directory exists
            }

            if (uiImage != null && !uiImage.isEmpty()) {
                // ✅ Fix filename: Remove spaces, keep original filename
                String filename = uiImage.getOriginalFilename().replaceAll(" ", "_");
                File destination = new File(uploadFolder, filename);
                uiImage.transferTo(destination);

                // ✅ Debug log to verify correct file path
                System.out.println("Saving file to: " + destination.getAbsolutePath());

                // ✅ Store the accessible image path
                imagePath = "/uploads/" + filename;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "errorPage"; // Redirect to error page if image upload fails
        }

        // ✅ Save project status
        ProjectStatus projectStatus = new ProjectStatus();
        projectStatus.setProjectId(projectStatusDTO.getProjectId());
        projectStatus.setStudentId(studentId);
        projectStatus.setPhase(projectStatusDTO.getPhase());
        projectStatus.setUpdateDescription(projectStatusDTO.getUpdateDescription());
        projectStatus.setUiImage(imagePath);
        projectStatus.setUpdatedAt(new Date());

        boolean isUpdated = projectStatusDAO.saveProjectStatus(projectStatus);

        if (!isUpdated) {
            System.out.println("Database update failed!");
            return "errorPage";
        }

        return "redirect:/student/dashboard";
    }
}
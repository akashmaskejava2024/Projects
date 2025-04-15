package com.nt.controller;

import com.nt.dao.ProfessorDAO;
import com.nt.dao.ProjectStatusDAO;
import com.nt.entity.Professor;
import com.nt.entity.ProjectStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorDAO professorDAO;

    @Autowired
    private ProjectStatusDAO projectStatusDAO;

    @GetMapping("/signup")
    public String showSignupPage() {
        return "professor/signup"; // Redirects to professorSignup.jsp
    }

    // ✅ Handle Signup Form Submission
    @PostMapping("/signup")
    public String signup(@RequestParam String name, 
                         @RequestParam String email, 
                         @RequestParam String password, 
                         Model model) {
        Professor professor = new Professor();
        professor.setName(name);
        professor.setEmail(email);
        professor.setPassword(password);

        boolean isRegistered = professorDAO.registerProfessor(professor);
        if (!isRegistered) {
            model.addAttribute("error", "Email already exists!");
            return "professor/signup"; // Stay on signup page if registration fails
        }

        return "redirect:/professor/login"; // Redirect to login page after successful signup
    }

    // ✅ Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "professor/login"; // Redirects to professorLogin.jsp
    }

    // ✅ Handle Login Form Submission
    @PostMapping("/login")
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        HttpSession session, 
                        Model model) {
        Professor professor = professorDAO.validateProfessor(email, password);

        if (professor == null) {
            model.addAttribute("error", "Invalid email or password!");
            return "professor/login"; // Stay on login page if authentication fails
        }

        // ✅ Store Professor ID in session
        session.setAttribute("professorId", professor.getId());
        session.setAttribute("professorName", professor.getName());

        return "redirect:/professor/dashboard"; // Redirect to dashboard after login
    }

    // ✅ Professor Dashboard (View Students' Project Statuses)
    @GetMapping("/dashboard")
    public String professorDashboard(HttpSession session, Model model) {
        Long professorId = (Long) session.getAttribute("professorId");
        if (professorId == null) {
            return "redirect:/professor/login"; // Redirect if not logged in
        }

        List<ProjectStatus> projectStatuses = projectStatusDAO.getProjectStatusesByProfessor(professorId);
        model.addAttribute("projectStatuses", projectStatuses);

        return "professor/dashboard"; // Redirects to professorDashboard.jsp
    }

    @PostMapping("/addFeedback")
    public String addFeedback(@RequestParam("projectStatusId") Long projectStatusId,
                              @RequestParam("feedback") String feedback,
                              HttpSession session) {
        Long professorId = (Long) session.getAttribute("professorId");
        if (professorId == null) {
            return "redirect:/professor/login"; // Redirect if not logged in
        }

        // Update feedback in the database
        projectStatusDAO.addFeedback(projectStatusId, feedback);

        return "redirect:/professor/dashboard"; // Refresh the dashboard
    }
    

    // ✅ Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/professor/login"; // Redirect to login after logout
    }
}
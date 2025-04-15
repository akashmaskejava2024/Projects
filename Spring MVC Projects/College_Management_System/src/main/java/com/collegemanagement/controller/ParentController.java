package com.collegemanagement.controller;

import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Parent;
import com.collegemanagement.entity.Student;
import com.collegemanagement.service.CourseService;
import com.collegemanagement.service.ParentService;
import com.collegemanagement.service.StudentPerformanceService;
import com.collegemanagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/parent")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @Autowired
    private StudentPerformanceService studentPerformanceService;
    
    @Autowired
    private CourseService courseService;
    
    
    @Autowired
    private StudentService studentService;
    @GetMapping("/signup")
    public String showSignupForm() {
        return "parent/signup";
    }
    
    
    @GetMapping("/login")
    public String showLoginForm() {
        return "parent/login";
    }
    
    
    @PostMapping("/login")
    public String authenticateParent(@RequestParam String email, 
                                     @RequestParam String password, 
                                     HttpSession session, 
                                     Model model) {
        Parent parent = parentService.getParentByEmail(email);

        if (parent != null && parent.getPassword().equals(password)) { // Ideally, use password hashing
            session.setAttribute("loggedInParent", parent);
            return "redirect:/parent/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "parent/login";
        }
    }
    @PostMapping("/register")
    public String registerParent(@ModelAttribute Parent parent) {
        parentService.addParent(parent);
        return "redirect:/admin/parents";
    }
    
    
    @GetMapping("/dashboard")
    public String showStudentPerformance( Model model, HttpSession session) {
        Parent loggedInParent =  (Parent) session.getAttribute("loggedInParent");
        int studentId  = loggedInParent.getStudentId();
        List<Map<String, Object>> attendanceList = studentPerformanceService.getMonthlyAttendance(studentId);
        List<Map<String, Object>> subjectWiseScores = studentPerformanceService.getSubjectWiseExamScores(studentId);
        List<Activity> activities = studentPerformanceService.getActivities(studentId);

        System.out.println("Attendance Data: " + attendanceList);
        System.out.println("Subject-wise Exam Scores: " + subjectWiseScores);

        List<Course> courses = courseService.getAllCourses(); // Fetch courses from DB
        Map<Integer, String> courseMap = new HashMap<>();

        for (Course course : courses) {
            courseMap.put(course.getId(), course.getName()); // Map courseId -> courseName
        }

        model.addAttribute("courseMap", courseMap); // Send the map to JSP
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("subjectWiseScores", subjectWiseScores);
        model.addAttribute("activities", activities);
        model.addAttribute("courses", courses);

        return "parent/dashboard"; // JSP Page
    }

    @GetMapping("/list")
    public String listParents(Model model) {
        List<Parent> parents = parentService.getAllParents();
        model.addAttribute("parents", parents);
        return "admin/manage-parents";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Parent getParentById(@PathVariable int id) {
        return parentService.getParentById(id);
    }

    @PostMapping("/add")
    public String addParent(@ModelAttribute Parent parent) {
        parentService.addParent(parent);
        return "redirect:/parent/list";
    }
    
    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam("id") int id, Model model) {
        Parent parent = parentService.getParentById(id);
        model.addAttribute("parent", parent);
        return "parent/signup"; // Reusing signup.jsp for both create and update
    }

    @PostMapping("/update")
    public String updateParent(@ModelAttribute Parent parent) {
        parentService.updateParent(parent);
        return "redirect:/admin/parents";
    }

    @GetMapping("/delete/{id}")
    public String deleteParent(@PathVariable int id) {
        parentService.deleteParent(id);
        return "redirect:/parent/list";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/parent/login";
    }
}

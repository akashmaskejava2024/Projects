package com.collegemanagement.controller;

import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Batch;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Student;
import com.collegemanagement.entity.StudentAcademicInfo;
import com.collegemanagement.service.BatchService;
import com.collegemanagement.service.CourseService;
import com.collegemanagement.service.StudentAcademicInfoService;
import com.collegemanagement.service.StudentCourseService;
import com.collegemanagement.service.StudentPerformanceService;
import com.collegemanagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentPerformanceService studentPerformanceService;
    
    @Autowired
    private BatchService batchService;
    
    @Autowired
    private StudentAcademicInfoService studentAcademicInfoService;
    
    @Autowired
    private StudentCourseService studentCourseService;
    
   
    @GetMapping("/login")
    public String showLoginPage() {
        return "student/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Student student = studentService.loginStudent(email, password);
        if (student != null) {
            session.setAttribute("studentId", student.getId());
            return "redirect:/student/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "student/login";
        }
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        List<Batch> batches = batchService.getAllBatches();

        // Fetch Course Names and create a map (courseId -> courseName)
        Map<Integer, String> courseMap = courseService.getAllCourses()
                .stream()
                .collect(Collectors.toMap(course -> course.getId(), course -> course.getName()));

        // Create a batch-course map (batchId -> courseName)
        Map<Integer, String> batchCourseMap = batches.stream()
                .collect(Collectors.toMap(Batch::getId, batch -> courseMap.getOrDefault(batch.getCourseId(), "Unknown Course")));

        model.addAttribute("batches", batches);
        model.addAttribute("batchCourseMap", batchCourseMap); // Send separately

        return "student/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Student student,
                         @RequestParam("batchId") int batchId,
                         Model model) {

        // Get the selected batch
        Batch batch = batchService.getBatchById(batchId);
        
        int semester = batch.getSemester();
        int courseId = batch.getCourseId();
        int professorId = batch.getProfessorId();

        // Determine academic year from semester
        int academicYear;
        if (semester == 1 || semester == 2) {
            academicYear = 1;
        } else if (semester == 3 || semester == 4) {
            academicYear = 2;
        } else if (semester == 5 || semester == 6) {
            academicYear = 3;
        } else {
            academicYear = 4;
        }

        // Save student with professorId
        student.setProfessorId(professorId);
        studentService.registerStudent(student);

        // Fetch the registered student
        Student st = studentService.loginStudent(student.getEmail(), student.getPassword());

        // Get current academic year (e.g., 2024-25)
        String calendarYear = getCurrentCalendarYear();

        // Save academic info
        studentAcademicInfoService.saveOrUpdate(
            new StudentAcademicInfo(st.getId(), academicYear, calendarYear)
        );

        // Assign course to student
        studentCourseService.assignCourse(st.getId(), courseId, professorId, semester);

        model.addAttribute("message", "Registration successful! Please log in.");
        return "redirect:/admin/students";
    }
    private String getCurrentCalendarYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int startYear, endYear;

        // If the current month is before June, use the previous year as startYear
        if (currentDate.getMonthValue() < 6) {
            startYear = currentYear - 1;
        } else {
            startYear = currentYear;
        }

        endYear = startYear % 100 + 1; // Convert 2024 to 24, then add 1 → 25
        return startYear + "-" + String.format("%02d", endYear); // Format as "2024-25"
    }
  
//    // Show edit profile form
//    @GetMapping("/profile")
//    public String showProfileForm(Model model, HttpSession session) {
//        int studentId = (int) session.getAttribute("studentId");
//        model.addAttribute("student", studentService.getStudentById(studentId));
//        return "student/profile";
//    }
    
    @GetMapping("/profile/{id}")
    public String showProfileForm( @PathVariable ("id") int studentId,Model model, HttpSession session) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        return "student/profile";
    }

    // Handle profile update
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Student student,@RequestParam int studentId, HttpSession session) {
        student.setId(studentId);
        studentService.updateStudentProfile(student);
        return "redirect:/admin/students";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
    
    

    @GetMapping("/dashboard")
    public String showStudentPerformance( Model model, HttpSession session) {
        int studentId = (int) session.getAttribute("studentId");
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

        return "student/dashboard"; // JSP Page
    }
}
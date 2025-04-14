package com.collegemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Attendance;
import com.collegemanagement.entity.Batch;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.ExamScore;
import com.collegemanagement.entity.Professor;
import com.collegemanagement.entity.Student;
import com.collegemanagement.entity.StudentCourse;
import com.collegemanagement.entity.StudentExamScore;
import com.collegemanagement.entity.Subject;
import com.collegemanagement.service.ActivityService;
import com.collegemanagement.service.BatchService;
import com.collegemanagement.service.CourseService;
import com.collegemanagement.service.ProfessorService;
import com.collegemanagement.service.StudentAcademicInfoService;
import com.collegemanagement.service.StudentCourseService;
import com.collegemanagement.service.StudentExamScoreService;
import com.collegemanagement.service.StudentPerformanceService;
import com.collegemanagement.service.StudentService;
import com.collegemanagement.service.SubjectService;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/professor")
@Controller
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;
    
    @Autowired
    private StudentCourseService studentCourseService;
    
    
    @Autowired
    private StudentService studentService;
    
    @Autowired 
    private CourseService courseService;
    
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private StudentPerformanceService studentPerformanceService;
    
    
    
    @Autowired
    private StudentExamScoreService studentExamScoreService;
    
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private BatchService batchService;
    
    @Autowired
    private StudentAcademicInfoService studentAcademicInfoService;
    
   
    
    // Show Login Page
    @RequestMapping("/login")
    public String showLoginPage() {
        return "professor/login";
    }

    @PostMapping("/logout")
    public String logoutProfessor(HttpSession session) {
        session.invalidate(); // Clear session data
        return "redirect:/professor/login"; // Redirect to login page
    }
    // Process Login
    @PostMapping("/login")
    public String loginProfessor(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Optional<Professor> professor = professorService.loginProfessor(email, password);
        if (professor.isPresent()) {
            session.setAttribute("professorId", professor.get().getId());
            return "redirect:/professor/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "professor/login";
        }
    }

    // Show Signup Page
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/signup";
    }
    
    // Process Signup
    @PostMapping("/signup")
    public String signupProfessor(@ModelAttribute Professor professor, Model model) {
        String result = professorService.registerProfessor(professor);
        if (result.equals("Professor registered successfully!")) {
            return "redirect:/admin/professors";
        } else {
            model.addAttribute("error", result);
            return "professor/signup";
        }
    }
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Integer professorId = (Integer) session.getAttribute("professorId");

        if (professorId == null) {
            return "redirect:/professor/login";
        }

        Optional<Professor> professorOpt = professorService.getProfessorById(professorId);
        if (!professorOpt.isPresent()) {
            return "redirect:/professor/login";
        }

        Professor professor = professorOpt.get();
        List<Student> students = professorService.getAssignedStudents(professorId);

        Map<Integer, List<Course>> studentCoursesMap = studentCourseService.getCoursesForStudents(students);

        // ✅ New: Student to semester map
        Map<Integer, Integer> studentSemesterMap = new HashMap<>();
        for (Student student : students) {
            Integer semester = studentCourseService.getSemesterByStudentAndProfessor(student.getId(), professorId);
            studentSemesterMap.put(student.getId(), semester);
        }

        model.addAttribute("professor", professor);
        model.addAttribute("students", students);
        model.addAttribute("studentCoursesMap", studentCoursesMap);
        model.addAttribute("studentSemesterMap", studentSemesterMap); // ✅ send to JSP

        return "professor/dashboard";
    }
    
    
    @PostMapping("/assign-course")
    public String assignCourse(@RequestParam int studentId, @RequestParam int courseId, HttpSession session) {
        Integer professorId = (Integer) session.getAttribute("professorId");
        if (professorId == null) {
            return "redirect:/professor/login";
        }
        studentCourseService.assignCourse(studentId, courseId, professorId,1);
        return "redirect:/professor/dashboard?success=courseAssigned";
    }

    
    @GetMapping("/assign-course")
    public String showAssignCoursePage(Model model) {
        model.addAttribute("students", studentCourseService.getAllStudents());
        model.addAttribute("courses", studentCourseService.getAllCourses());
        return "professor/assignCourse";
    }

    

 // ✅ Show Edit Profile Page
    @GetMapping("/edit-profile/{id}")
    public String showEditProfilePage(@PathVariable ("id") int professorId, Model model, HttpSession session) {

        Optional<Professor> professor = professorService.getProfessorById(professorId);
        model.addAttribute("professor", professor.orElse(null));
        return "professor/edit-profile";
    }

    
    // ✅ Process Profile Update
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute Professor professor,
                                @RequestParam(value = "password", required = false) String password,
                                HttpSession session, Model model) {
       

        professor.setId(professor.getId()); // Ensure correct ID is used

        // If the password is provided, update it
        if (password != null && !password.trim().isEmpty()) {
            professor.setPassword(password);
        }

        String result = professorService.updateProfessor(professor);

        if (result.equals("Professor updated successfully!")) {
        	return "redirect:/admin/professors";
        } else {
            model.addAttribute("error", result);
            return "redirect:/admin/professors";
        }
    }

    // Delete Professor
    @PostMapping("/delete")
    public String deleteProfessor(HttpSession session) {
        Integer professorId = (Integer) session.getAttribute("professorId");
        if (professorId != null) {
            professorService.deleteProfessor(professorId);
            session.invalidate(); // Logout professor after deletion
        }
        return "redirect:/professor/login";
    }
    @GetMapping("/add-score")
    public String addExamScorePage(
        @RequestParam("studentId") int studentId,
        @RequestParam("semester") int semester,
        Model model, HttpSession session) {

        Integer professorId = (Integer) session.getAttribute("professorId");
        if (professorId == null) {
            return "redirect:/professor/login";
        }

        Student student = studentService.getStudentById(studentId);
        StudentCourse course = studentCourseService.getStudentCourseByStudentId(studentId, semester);

        Course course2 = courseService.getCourseById(course.getCourseId());
        
       

        List<Subject> subjects = subjectService.getSubjectsByCourseAndSemester(course.getCourseId(), semester);

        model.addAttribute("student", student);
        model.addAttribute("semester", semester);
        model.addAttribute("subjects", subjects);
        model.addAttribute("courseName",course2.getName() ); // ✅ Add course name

        return "professor/add-scores";
    }
 // Handle Form Submission
    @PostMapping("/add-score")
    public String addExamScores(HttpServletRequest request) {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        List<StudentExamScore> scores = new ArrayList<>();

        String[] subjectIds = request.getParameterValues("subjectId");
        int currentSemester = 0;

        if (subjectIds != null) {
            for (String subjectIdStr : subjectIds) {
                int subjectId = Integer.parseInt(subjectIdStr);
                int internalMarksObtained = Integer.parseInt(request.getParameter("internalMarksObtained_" + subjectId));
                int internalMarksTotal = Integer.parseInt(request.getParameter("internalMarksTotal_" + subjectId));
                int externalMarksObtained = Integer.parseInt(request.getParameter("externalMarksObtained_" + subjectId));
                int externalMarksTotal = Integer.parseInt(request.getParameter("externalMarksTotal_" + subjectId));

                int totalMarksObtained = internalMarksObtained + externalMarksObtained;
                int totalMarksMax = internalMarksTotal + externalMarksTotal;

                StudentExamScore score = new StudentExamScore();
                score.setStudentId(studentId);
                score.setSubjectId(subjectId);
                score.setInternalMarksObtained(internalMarksObtained);
                score.setInternalMarksTotal(internalMarksTotal);
                score.setExternalMarksObtained(externalMarksObtained);
                score.setExternalMarksTotal(externalMarksTotal);
                score.setTotalMarksObtained(totalMarksObtained);
                score.setTotalMarksMax(totalMarksMax);

                scores.add(score);

                Subject s = subjectService.getSubjectById(subjectId);
                currentSemester = s.getSemester(); // overwrites each time, assuming all subjects from same semester
            }
        }

        // Store exam scores
        studentExamScoreService.addExamScores(scores);

        // Get student and course details
        Student student = studentService.getStudentById(studentId);
        List<Course> courses = studentCourseService.getCoursesForStudent(studentId);
        int courseId = courses.isEmpty() ? 0 : courses.get(0).getId();

        // Get the current batch (throws exception if not found)
        Batch currentBatch = batchService.getBatchByCourseAndSemester(courseId, currentSemester);
        int currentProfessorId = currentBatch.getProfessorId();

        // Count subjects
        int totalSubjects = subjectService.getTotalSubjectsForSemester(courseId, currentSemester);
        int addedSubjectsCount = studentExamScoreService.getSubjectCountForStudentInSemester(studentId, currentSemester);

        // If all scores submitted, promote to next semester
        if (addedSubjectsCount == totalSubjects) {
            int newSemester = currentSemester + 1;

            try {
                Batch newBatch = batchService.getBatchByCourseAndSemester(courseId, newSemester); // throws if not found
                int newProfessorId = newBatch.getProfessorId();

                studentService.updateStudentAndProfessor(studentId, newProfessorId);
                studentCourseService.updateStudentCourse(studentId, courseId, newProfessorId, newSemester);

                String newCalendarYear = getCurrentCalendarYear();
                int newAcademicYear = (newSemester + 1) / 2;
                studentAcademicInfoService.updateAcademicInfo(studentId, newAcademicYear, newCalendarYear);

            } catch (RuntimeException e) {
                // Log and continue if next batch not found
                System.out.println("⚠️ Next semester batch not found: " + e.getMessage());
            }
        }

        return "redirect:/professor/dashboard";
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

    // ✅ Show Mark Attendance Page (Now inside Dashboard)
    @GetMapping("/mark-attendance")
    public String showMarkAttendancePage(Model model, HttpSession session) {
        Integer professorId = (Integer) session.getAttribute("professorId");

        if (professorId == null) {
            return "redirect:/professor/login";
        }

        List<Student> students = professorService.getAssignedStudents(professorId);
        model.addAttribute("students", students);
        return "professor/mark-attendance"; // Now part of the dashboard
    }
    @PostMapping("/mark-attendance")
    public String markAttendance(@RequestParam("date") String date,
                                 @RequestParam(value = "presentStudents", required = false) List<Integer> presentStudents,
                                 HttpSession session) {
        // Retrieve professor ID from session
        Integer professorId = (Integer) session.getAttribute("professorId");

        if (professorId == null) {
            return "redirect:/professor/login";
        }

        // Ensure presentStudents is not null to avoid NullPointerException
        if (presentStudents == null) {
            presentStudents = new ArrayList<>();
        }

        professorService.markAttendanceForAll(date, presentStudents, professorId);
        return "redirect:/professor/dashboard";
    }
    
    
    
     
 // Show Add Activity Form
    @GetMapping("/add-activity")
    public String showAddActivityForm(@RequestParam("studentId") int studentId, Model model) {
        model.addAttribute("studentId", studentId);
        return "professor/add-activity";  // JSP Page for adding activity
    }
    @PostMapping("/add-activity")
    public String addActivity(
            HttpServletRequest request,
            @RequestParam("studentId") int studentId,
            @RequestParam String type,
            @RequestParam String subtype,
            @RequestParam String competitionLevel,
            @RequestParam(value = "rank", required = false) Integer rank,
            @RequestParam(value = "achievement") String achievement,
            @RequestParam(value = "image", required = true) MultipartFile image) {

        if (studentId == 0) {
            throw new IllegalArgumentException("Student ID cannot be zero or missing");
        }

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            try {
                // ✅ Get path to /uploads/ folder inside the deployed webapp
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);

                File uploadDir = new File(realPathtoUploads);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Save the file
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(realPathtoUploads, filename);
                Files.write(filePath, image.getBytes());

                // Save relative path to DB
                imagePath = uploadsDir + filename;

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to save image file.");
            }
        }

        Activity activity = new Activity();
        activity.setStudentId(studentId);
        activity.setType(type);
        activity.setSubtype(subtype);
        activity.setCompetitionLevel(competitionLevel);
        activity.setRank(rank != null ? rank : 0);
        activity.setAchievement(achievement);
        activity.setImage(imagePath);

        activityService.addActivity(activity);

        return "redirect:/professor/dashboard";
    }
    
    
    
    @GetMapping("/student/performance")
    public String showStudentPerformance(@RequestParam("studentId") int studentId, Model model) {
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

        return "professor/student-performance"; // JSP Page
    }
}
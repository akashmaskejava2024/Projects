package com.collegemanagement.controller;

import com.collegemanagement.entity.Activity;
import com.collegemanagement.entity.Admin;
import com.collegemanagement.entity.Batch;
import com.collegemanagement.entity.Course;
import com.collegemanagement.entity.Student;
import com.collegemanagement.entity.StudentAcademicInfo;
import com.collegemanagement.entity.StudentCourse;
import com.collegemanagement.entity.Subject;
import com.collegemanagement.entity.Professor;
import com.collegemanagement.entity.Parent;
import com.collegemanagement.service.AdminService;
import com.collegemanagement.service.BatchService;
import com.collegemanagement.service.CourseService;
import com.collegemanagement.service.StudentService;
import com.collegemanagement.service.SubjectService;
import com.collegemanagement.service.ProfessorService;
import com.collegemanagement.service.StudentAcademicInfoService;
import com.collegemanagement.service.StudentCourseService;
import com.collegemanagement.service.StudentPerformanceService;
import com.collegemanagement.service.ParentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	private final StudentService studentService;
	private final ProfessorService professorService;
	private final ParentService parentService;
	private final CourseService courseService;
	private final StudentCourseService studentCourseService;
	private final StudentAcademicInfoService studentAcademicInfoService;
	private final SubjectService subjectService;
	private final BatchService batchService;
	private StudentPerformanceService studentPerformanceService;

	public AdminController(AdminService adminService, StudentService studentService, ProfessorService professorService,
			ParentService parentService, CourseService courseService, StudentCourseService studentCourseService,
			StudentAcademicInfoService studentAcademicInfoService, SubjectService subjectService,BatchService batchService, StudentPerformanceService studentPerformanceService) {
		this.adminService = adminService;
		this.studentService = studentService;
		this.professorService = professorService;
		this.parentService = parentService;
		this.courseService = courseService;
		this.studentCourseService = studentCourseService;
		this.studentAcademicInfoService = studentAcademicInfoService;
		this.subjectService = subjectService;
		this.batchService = batchService;
		this.studentPerformanceService = studentPerformanceService;
	}

	@PostMapping("/professors/assignBatch")
	public String assignBatch(@RequestParam("professorId") int professorId,
	                          @RequestParam("courseId") int courseId,
	                          @RequestParam("semester") int semester,
	                          RedirectAttributes redirectAttributes) {
	    boolean assigned = batchService.assignBatchToProfessor(professorId, courseId, semester);
	    
	    if (assigned) {
	        redirectAttributes.addFlashAttribute("successMessage", "Batch assigned successfully!");
	        return "redirect:/admin/professors";
	    } else {
	        redirectAttributes.addFlashAttribute("errorMessage", "This batch is already assigned to a professor.");
	        return "redirect:/admin/professors"; // Redirect to the same page
	    }
	}
	 
	 @GetMapping("/professors/changeBatch/{professorId}")
	 public String showChangeBatchForm(@PathVariable int professorId, Model model) {
	     // Fetch batches assigned to the current professor
	     List<Batch> currentProfessorBatches = batchService.getBatchesByProfessorId(professorId);

	     // Fetch all professors except the current one
	     List<Professor> availableProfessors = professorService.getAllProfessors();
	     availableProfessors.removeIf(p -> p.getId() == professorId);

	     // Map courseId → courseName for dropdown
	     Map<Integer, String> courseMap = new HashMap<>();
	     for (Batch batch : currentProfessorBatches) {
	         if (!courseMap.containsKey(batch.getCourseId())) {
	             courseMap.put(batch.getCourseId(), courseService.getCourseById(batch.getCourseId()).getName());
	         }
	     }

	     model.addAttribute("currentProfessorBatches", currentProfessorBatches);
	     model.addAttribute("availableProfessors", availableProfessors);
	     model.addAttribute("courseMap", courseMap);
	     
	     return "admin/changeBatchForm"; // JSP page
	 }

	 @PostMapping("/professors/changeBatch")
	 public String changeBatchProfessor(@RequestParam int currentProfessorId,
	                                    @RequestParam int newProfessorId,
	                                    @RequestParam int courseId,
	                                    @RequestParam int semester,
	                                    RedirectAttributes redirectAttributes) {
	     String message = professorService.updateProfessorForBatch(currentProfessorId, newProfessorId, courseId, semester);
	     redirectAttributes.addFlashAttribute("message", message);
	     return "redirect:/admin/professors";
	 }
	
	
	// Show Admin Login Page
	@GetMapping("/login")
	public String showLoginPage() {
		return "admin/login";
	}

	// Process Admin Login
	@PostMapping("/login")
	public String loginAdmin(@RequestParam String email, @RequestParam String password, HttpSession session,
			Model model) {
		Admin admin = adminService.validateAdmin(email, password);

		if (admin != null) {
			session.setAttribute("loggedInAdmin", admin);
			return "redirect:/admin/dashboard";
		} else {
			model.addAttribute("error", "Invalid email or password");
			return "admin/login";
		}
	}

	 @GetMapping("/dashboard")
	    public String dashboard(HttpSession session, Model model) {
	        if (session.getAttribute("loggedInAdmin") == null) {
	            return "redirect:/admin/login";
	        }

	        int totalStudents = studentService.getTotalStudents();
	        int totalProfessors = professorService.getTotalProfessors();
	        int totalCourses = courseService.getTotalCourses();

	        model.addAttribute("totalStudents", totalStudents);
	        model.addAttribute("totalProfessors", totalProfessors);
	        model.addAttribute("totalCourses", totalCourses);

	        return "admin/dashboard";
	    }
	// Admin Logout
	@GetMapping("/logout")
	public String logoutAdmin(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}

	// Manage Students
	@GetMapping("/students")
	public String manageStudents(Model model, HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}

		List<Student> students = studentService.getAllStudents();
		Map<Integer, String> professorNames = new HashMap<>();
		Map<Integer, String> courseNames = new HashMap<>();
		Map<Integer, String> academicYears = new HashMap<>();
		Map<Integer, String> calendarYears = new HashMap<>();

		// Sets to store unique years for filtering
		Set<String> uniqueAcademicYears = new TreeSet<>(Comparator.reverseOrder());
		Set<String> uniqueCalendarYears = new TreeSet<>(Comparator.reverseOrder());

		for (Student student : students) {
			// Fetch professor name
			professorService.getProfessorById(student.getProfessorId())
					.ifPresent(professor -> professorNames.put(student.getId(), professor.getName()));

			// Fetch course name (Assuming a student has only one course)
			List<Course> courses = studentCourseService.getCoursesForStudent(student.getId());
			if (!courses.isEmpty()) {
				courseNames.put(student.getId(), courses.get(0).getName()); // Taking the first course
			}

			// Fetch academic and calendar year
			Optional<StudentAcademicInfo> academicInfo = studentAcademicInfoService.getByStudentId(student.getId());
			academicInfo.ifPresent(info -> {
				String academicYearStr = info.getAcademicYear() + " Year";
				academicYears.put(student.getId(), academicYearStr);
				calendarYears.put(student.getId(), info.getCalendarYear());

				// Add to unique sets for dropdown
				uniqueAcademicYears.add(academicYearStr);
				uniqueCalendarYears.add(info.getCalendarYear());
			});
		}

		model.addAttribute("students", students);
		model.addAttribute("professorNames", professorNames);
		model.addAttribute("courseNames", courseNames);
		model.addAttribute("academicYears", academicYears);
		model.addAttribute("calendarYears", calendarYears);

		// Pass unique academic and calendar years for filtering
		model.addAttribute("uniqueAcademicYears", uniqueAcademicYears);
		model.addAttribute("uniqueCalendarYears", uniqueCalendarYears);

		return "admin/manage-students";
	}

	@GetMapping("/sorted/students")
	public String manageStudents(@RequestParam(name = "calendarYear", required = false) String calendarYear,
			@RequestParam(name = "academicYear", required = false) Integer academicYear,
			@RequestParam(name = "course", required = false) String course, Model model, HttpSession session) {

		// Check if the admin is logged in
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}

		// Retrieve all students
		List<Student> students = studentService.getAllStudents();
		Map<Integer, String> professorNames = new HashMap<>();
		Map<Integer, String> courseNames = new HashMap<>();
		Map<Integer, Integer> academicYears = new HashMap<>();
		Map<Integer, String> calendarYears = new HashMap<>();
		Set<String> uniqueCourses = new HashSet<>();
		Set<String> uniqueCalendarYears = new HashSet<>();
		Set<Integer> uniqueAcademicYears = new HashSet<>();

		// Populate details
		for (Student student : students) {
			professorService.getProfessorById(student.getProfessorId())
					.ifPresent(professor -> professorNames.put(student.getId(), professor.getName()));

			List<Course> courses = studentCourseService.getCoursesForStudent(student.getId());
			if (!courses.isEmpty()) {
				String courseName = courses.get(0).getName();
				courseNames.put(student.getId(), courseName);
				uniqueCourses.add(courseName);
			}

			studentAcademicInfoService.getByStudentId(student.getId()).ifPresent(info -> {
				academicYears.put(student.getId(), info.getAcademicYear());
				calendarYears.put(student.getId(), info.getCalendarYear());
				uniqueAcademicYears.add(info.getAcademicYear());
				uniqueCalendarYears.add(info.getCalendarYear());
			});
		}

		// Convert empty values to null for proper filtering
		if (calendarYear != null && calendarYear.trim().isEmpty()) {
			calendarYear = null;
		}
		if (course != null && course.trim().isEmpty()) {
			course = null;
		}

		// Assign final variables to avoid lambda capture issues
		final String finalCalendarYear = calendarYear;
		final Integer finalAcademicYear = academicYear;
		final String finalCourse = course;

		students = students.stream()
				.filter(student -> finalCalendarYear == null
						|| Objects.equals(finalCalendarYear, calendarYears.get(student.getId())))
				.filter(student -> finalAcademicYear == null
						|| finalAcademicYear.equals(academicYears.get(student.getId())))
				.filter(student -> finalCourse == null || Objects.equals(finalCourse, courseNames.get(student.getId())))
				.collect(Collectors.toList());
		// Add attributes to model
		model.addAttribute("students", students);
		model.addAttribute("professorNames", professorNames);
		model.addAttribute("courseNames", courseNames);
		model.addAttribute("academicYears", academicYears);
		model.addAttribute("calendarYears", calendarYears);

		model.addAttribute("uniqueCourses", uniqueCourses);
		model.addAttribute("uniqueCalendarYears", uniqueCalendarYears);
		model.addAttribute("uniqueAcademicYears", uniqueAcademicYears);

		model.addAttribute("selectedCalendarYear", calendarYear);
		model.addAttribute("selectedAcademicYear", academicYear);
		model.addAttribute("selectedCourse", course);

		return "admin/manage-students";
	}

	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}
		studentService.deleteStudent(id);
		return "redirect:/admin/students";
	}
	
	
	@GetMapping("/professors")
	public String manageProfessors(Model model, HttpSession session, 
	                               @ModelAttribute("successMessage") String successMessage,
	                               @ModelAttribute("errorMessage") String errorMessage) {
	    if (session.getAttribute("loggedInAdmin") == null) {
	        return "redirect:/admin/login";
	    }

	    List<Professor> professors = professorService.getAllProfessors();
	    List<Course> courses = courseService.getAllCourses();

	    Map<Integer, List<String>> professorCoursesMap = new HashMap<>();
	    Map<Integer, List<Batch>> professorBatchesMap = new HashMap<>();
	    Map<Integer, String> courseMap = new HashMap<>();

	    for (Course course : courses) {
	        courseMap.put(course.getId(), course.getName());
	    }

	    for (Professor professor : professors) {
	        List<StudentCourse> studentCourses = studentCourseService.getCoursesByProfessorId(professor.getId());
	        List<Batch> batches = batchService.getBatchesByProfessorId(professor.getId());

	        Map<String, Set<Integer>> courseSemesterMap = new LinkedHashMap<>();
	        for (StudentCourse studentCourse : studentCourses) {
	            String courseName = courseMap.get(studentCourse.getCourseId());
	            if (courseName != null) {
	                courseSemesterMap
	                    .computeIfAbsent(courseName, k -> new TreeSet<>())
	                    .add(studentCourse.getSemester());
	            }
	        }

	        List<String> formattedCourses = new ArrayList<>();
	        for (Map.Entry<String, Set<Integer>> entry : courseSemesterMap.entrySet()) {
	            String courseInfo = entry.getKey() + " (Semesters: " + entry.getValue().stream()
	                .map(String::valueOf)
	                .collect(Collectors.joining(", ")) + ")";
	            formattedCourses.add(courseInfo);
	        }

	        professorCoursesMap.put(professor.getId(), formattedCourses);
	        professorBatchesMap.put(professor.getId(), batches);
	    }
	    
	    Map<Integer, List<Integer>> courseSemesterMap = batchService.getUnassignedSemestersGroupedByCourse();
	    model.addAttribute("courseSemesterMap", courseSemesterMap);
	    model.addAttribute("professors", professors);
	    model.addAttribute("professorCoursesMap", professorCoursesMap);
	    model.addAttribute("professorBatchesMap", professorBatchesMap);
	    model.addAttribute("availableCourses", courses);
	    model.addAttribute("courseMap", courseMap);
	    model.addAttribute("successMessage", successMessage);
	    model.addAttribute("errorMessage", errorMessage);

	    return "admin/manage-professors";
	}
	
	
	
	@GetMapping("/professors/delete/{id}")
	public String deleteProfessor(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}
		professorService.deleteProfessor(id);
		return "redirect:/admin/professors";
	}

	// Manage Parents
	@GetMapping("/parents")
	public String manageParents(Model model, HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}

		List<Parent> parents = parentService.getAllParents();
		Map<Integer, String> studentNames = new HashMap<>();

		for (Parent parent : parents) {
			Student student = studentService.getStudentById(parent.getStudentId());
			if (student != null) {
				studentNames.put(parent.getId(), student.getName());
			} else {
				studentNames.put(parent.getId(), "Unknown"); // Handle null cases
			}
		}

		model.addAttribute("parents", parents);
		model.addAttribute("studentNames", studentNames);

		return "admin/manage-parents";
	}

	@GetMapping("/parents/delete/{id}")
	public String deleteParent(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}
		parentService.deleteParent(id);
		return "redirect:/admin/parents";
	}

	// View Reports
	@GetMapping("/reports")
	public String viewReports(HttpSession session) {
		if (session.getAttribute("loggedInAdmin") == null) {
			return "redirect:/admin/login";
		}
		return "admin/reports";
	}

	// Course Module

	@GetMapping("/courses")
	public String manageCourses(Model model) {
		List<Course> courses = courseService.getAllCourses();
		List<Professor> professors = professorService.getAllProfessors(); // Fetch professors

		model.addAttribute("courses", courses);
		model.addAttribute("professors", professors); // Add professors to model
		return "admin/manage-courses";
	}

	// Handle Course Addition
	@PostMapping("/courses/add")
	public String addCourse(@ModelAttribute Course course) {
		courseService.addCourse(course);
		return "redirect:/admin/courses";
	}

	// Delete Course
	@GetMapping("/courses/delete/{id}")
	public String deleteCourse(@PathVariable int id) {
		courseService.deleteCourse(id);
		return "redirect:/admin/courses";
	}

	@GetMapping("/courses/edit/{id}")
	public String editCourse(@PathVariable int id) {
		courseService.deleteCourse(id);
		return "redirect:/admin/courses";
	}

	@GetMapping("/getcourses")
	public String showCourses(Model model) {
		List<Course> courses = courseService.getAllCourses(); // Fetching courses
		List<Professor> professors = professorService.getAllProfessors(); // Fetching professors

		model.addAttribute("courses", courses);
		model.addAttribute("professors", professors);
		return "admin/manage-courses"; // This is the JSP page name
	}

	@GetMapping("/subjects/bulk-add")
	public String showBulkAddPage(@RequestParam("semester") int semester,
	                              @RequestParam("numSubjects") int numSubjects,
	                              @RequestParam("courseId") int courseId,
	                              Model model) {
	    model.addAttribute("semester", semester);
	    model.addAttribute("numSubjects", numSubjects);
	    model.addAttribute("courseId", courseId);
	    return "admin/bulk-add-subjects";
	}

	@GetMapping("/subjects/{courseId}")
	public String manageSubjects(@PathVariable("courseId") int courseId, Model model) {
	    Course course = courseService.getCourseById(courseId);
	    if (course == null) {
	        return "redirect:/admin/courses"; // Redirect if course doesn't exist
	    }

	    int totalSemesters = course.getSemester();
	    Map<Integer, List<Subject>> subjectsBySemester = new HashMap<>();

	    // Initialize all semesters with an empty list
	    for (int i = 1; i <= totalSemesters; i++) {
	        subjectsBySemester.put(i, new ArrayList<>());
	    }

	    List<Subject> subjects = subjectService.getSubjectsByCourse(courseId);
	    for (Subject subject : subjects) {
	        subjectsBySemester.get(subject.getSemester()).add(subject);
	    }

	    model.addAttribute("subjectsBySemester", subjectsBySemester);
	    model.addAttribute("courseName", course.getName());
	    model.addAttribute("semester", totalSemesters);
	    model.addAttribute("courseId", courseId);

	    return "admin/manage-subjects";
	}
	// Add a single subject for a specific course
	@PostMapping("/subjects/add")
	public String addSubject(@RequestParam String name, @RequestParam int courseId, @RequestParam int semester) {
		subjectService.addSubject(new Subject(0, name, courseId, semester));
		return "redirect:/admin/subjects/" + courseId;
	}

	// Delete a subject and stay on the same course's page
	@GetMapping("/subjects/delete/{id}")
	public String deleteSubject(@PathVariable int id, @RequestParam int courseId) {
		subjectService.deleteSubject(id);
		return "redirect:/admin/subjects/" + courseId;
	}
	
	

	 @PostMapping("/subjects/add-bulk")
	 public String addSubjectsBulk(@RequestParam int courseId, 
	                               @RequestParam int semester, 
	                               @RequestParam List<String> subjectNames) {
	     
	     for (String subjectName : subjectNames) {
	         if (subjectName != null && !subjectName.trim().isEmpty()) {
	             subjectService.addSubject(new Subject(0, subjectName, courseId, semester));
	         }
	     }
	     
	     return "redirect:/admin/subjects/" + courseId;
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

	        return "admin/student-performance"; // JSP Page
	    }
	 
}

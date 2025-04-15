package com.rt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.StudentEntity.StudentEntity;
import com.rt.entity.IssueBookEntity;
import com.rt.service.LibraryService;
import com.rt.service.issueBookService;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private LibraryService libraryService;

	@Autowired
	private issueBookService issueBookService;

	@RequestMapping("/add-form")
	public String studentAdd() {

		return "students/add";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute StudentEntity student, Model m) {

		boolean isAdded = libraryService.addStudent(student);
		if (isAdded) {
			m.addAttribute("successMsg", "student Added Successfully...");
		} else {
			m.addAttribute("errMsg", "Unable to Add...");
		}

		return "redirect:/students/list";
	}

	@RequestMapping("/update")
	public String editStudent() {
		return "students/update";
	}

	@PostMapping("/updateStudent")
	public String updateBook(@ModelAttribute StudentEntity student, Model model) {
		boolean isUpdated = libraryService.update(student);
		if (isUpdated) {
			model.addAttribute("successMsg", "Successfully updated.");
		} else {
			model.addAttribute("errMsg", "Not updated.");
		}
		return "redirect:/students/list";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam int studentId, Model model) {

		boolean empdelete = libraryService.delete(studentId);

		if (empdelete) {
			model.addAttribute("successMsg", "student deleted successfully...");
		} else {
			model.addAttribute("errMsg", "Unable to delete student...");
		}

		return "redirect:/students/list";
	}

	@GetMapping("/list")
	public String studentList(Model model) {
		List<StudentEntity> studentList = libraryService.all();
		model.addAttribute("studentList", studentList);
		return "students/list";
	}

	@GetMapping("/student-details/{studentId}")
	public String getStudentDetails(@PathVariable int studentId, Model model) {
		StudentEntity student = libraryService.getStudentById(studentId);
		if (student != null) {
			List<IssueBookEntity> issueBookList = issueBookService.getByUserId(studentId,"student");
			model.addAttribute("student", student);
			model.addAttribute("issueBookList", issueBookList);
			return "students/student-details";
		} else {
			return "error";
		}
	}

}

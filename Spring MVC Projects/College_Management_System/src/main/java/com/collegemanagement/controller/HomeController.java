package com.collegemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	
	@GetMapping("/")
	public String adminLoginPage() {
		return "admin/login";
	}
}

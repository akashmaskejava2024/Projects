package com.rt.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.entity.BookEntity;
import com.rt.entity.RegisterEntity;
import com.rt.service.BookService;
import com.rt.service.LibraryService;
import com.rt.service.RegisterService;
import com.rt.service.TeacherService;
import com.rt.service.issueBookService;

@Controller
public class LibraryController {

	@Autowired
	JdbcTemplate Template;

	@Autowired
	private BookService bookService;

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	issueBookService issueBookService;

	@Autowired
	RegisterService registerService;

	@RequestMapping("/")
	public String login() {
		return "other/new_login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "other/signup";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute RegisterEntity register,Model model) {
		boolean isRegistered = registerService.signUp(register);
		if (isRegistered) {
			model.addAttribute("successMsg", "User registered successfully. Please log in.");
			return "other/new_login";
		} else {
			model.addAttribute("errMsg", "Unable to register. Please try again.");
			return "other/signup";
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password,HttpSession session, Model model) {
		Map<String, Integer> quantities = bookService.calculateQuantities();

		// Add all data to the model
		model.addAttribute("numberOfBooks", quantities.get("TotalQuantity"));
		model.addAttribute("availableBooks", quantities.get("AvailableQuantity"));

		int numberOfStudents = libraryService.getnumberOfStudents();
		model.addAttribute("numberOfStudents", numberOfStudents);

		int numberOfTeachers = teacherService.getnumberOfTeachers();
		model.addAttribute("numberOfTeachers", numberOfTeachers);

		List<BookEntity> allBooks = bookService.all();
		model.addAttribute("allBook", allBooks);

		String sql = "SELECT COUNT(*) FROM librarian WHERE Username = ? AND Password = ?";
		int count = Template.queryForObject(sql, Integer.class, username, password);

		if (count == 1) {
	        session.setAttribute("loggedInUsername", username); // Set in session
	        model.addAttribute("loggedInUsername", username); // Set in model

		    return "Authontication/index";
		} else {
		    model.addAttribute("errMsg", "Invalid username or password.");
		    return "other/new_login";
		}


	}
	
	@GetMapping("/libraryindex")
	public String indexPage(HttpSession session, Model model) {
		
		Map<String, Integer> quantities = bookService.calculateQuantities();

		// Add all data to the model
				model.addAttribute("numberOfBooks", quantities.get("TotalQuantity"));
				model.addAttribute("availableBooks", quantities.get("AvailableQuantity"));

				int numberOfStudents = libraryService.getnumberOfStudents();
				model.addAttribute("numberOfStudents", numberOfStudents);

				int numberOfTeachers = teacherService.getnumberOfTeachers();
				model.addAttribute("numberOfTeachers", numberOfTeachers);

				List<BookEntity> allBooks = bookService.all();
				model.addAttribute("allBook", allBooks);
				
		       String username = (String) session.getAttribute("loggedInUsername"); // Set in session
		       model.addAttribute("loggedInUsername", username);
				
				  return "Authontication/index";
		
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
            new SecurityContextLogoutHandler().logout(request, response, auth);  
        }

        // Generate a random parameter to prevent caching
        String randomParam = UUID.randomUUID().toString();

        return "redirect:/?logout=true&random=" + randomParam;  
    }

   /* @RequestMapping(value="/logout", method=RequestMethod.GET)  
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
            new SecurityContextLogoutHandler().logout(request, response, auth);  
        }

        // Prevent caching of the previous page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0"); // Proxies.

        return "redirect:/";  
    }
*/
}

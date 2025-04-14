package  com.rt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rt.entity.IssueBookEntity;
import com.rt.service.BookService;
import com.rt.service.issueBookService;

@Controller
@RequestMapping("/books")
public class IssueBookController {

	@Autowired
	private issueBookService issueBookService;
	
	@Autowired
	private BookService bookService;
	


	@RequestMapping("/issueBook-form")
	public String showAddBorrowForm(Model model) {
		model.addAttribute("issueBook", new IssueBookEntity());
		return "books/issueBook";
	}

	@RequestMapping("/issue")
	public String addBorrow(@ModelAttribute IssueBookEntity issueBook, Model model) {
		try {
			boolean isAvailable = issueBookService.isBookAvailable(issueBook.getBookId());

			if (!isAvailable) {
				model.addAttribute("errMsg", "Book with ID " + issueBook.getBookId() + " is not available.");
				return "redirect:/books/issueBook-form";
			}

			boolean isAdded = issueBookService.add(issueBook);

			if (isAdded) {
				model.addAttribute("succMsg", "Borrow entry added successfully");
			} else {
				model.addAttribute("errorMsg", "Failed to add borrow entry");
			}
		} catch (Exception e) {
			model.addAttribute("errMesssge", "An error occurred: " + e.getMessage());
		}

		return "redirect:/books/issuerecord";
	}

	@RequestMapping("/issuerecord")
	public String bookHistory(Model model) {
		List<IssueBookEntity> list = issueBookService.issuerecord();
		model.addAttribute("issueBookList", list);
		return "books/issueBookRecord";
	}

	@PostMapping("/returnBook")
	public ModelAndView returnBook(@RequestParam("issueId") int issueId, @RequestParam("bookId") int bookId) {
		ModelAndView modelAndView = new ModelAndView("redirect:/books/issuerecord");
		try {
			issueBookService.updateStatus(issueId, "Submitted");
			bookService.increaseAvailableQuantity(bookId,"Available");
			modelAndView.addObject("successMessage", "Book returned successfully.");
		} catch (Exception e) {
			modelAndView.addObject("errorMessage", "Failed to return book: " + e.getMessage());
		}
		return modelAndView;
	}

}

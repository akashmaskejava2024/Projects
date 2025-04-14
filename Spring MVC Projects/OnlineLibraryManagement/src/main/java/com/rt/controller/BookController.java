package com.rt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.entity.BookEntity;
import com.rt.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	

	@RequestMapping("/add-form")
	public String addForm(){
	
		return "books/add";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute BookEntity bookEntity, Model model) {
		boolean isAdded = bookService.addBook(bookEntity);

		if (isAdded) {
			model.addAttribute("succMsg", "book is added succesfully");

		} else {
			model.addAttribute("errMsg", "book is not added ");

		}
		return "redirect:/books/list";

	}

	@RequestMapping("/update")
	public String BookUpdate() {
		return "books/update";

	}

	@PostMapping("/updateBook")
	public String updateBook(@ModelAttribute BookEntity entity, Model model) {
		boolean isUpdated = bookService.update(entity);
		if (isUpdated) {
			model.addAttribute("msgsucc", "Successfully updated.");
		} else {
			model.addAttribute("err", "Not updated.");
		}
		return "redirect:/books/list";
	}

	@RequestMapping("/delete")
	public String deletedata(@RequestParam int bookId, Model model) {

		boolean empdelete = bookService.deletedata(bookId);

		if (empdelete) {
			model.addAttribute("successMsg", "Book deleted successfully...");
		} else {
			model.addAttribute("errMsg", "Unable to delete the book...");
		}

		return "redirect:/books/list";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<BookEntity> list = bookService.all();
		model.addAttribute("allBook", list);
		
		
		 Map<Integer, Boolean> availabilityMap = new HashMap<>();
	        for (BookEntity book : list) {
	            boolean isAvailable = bookService.isBookAvailable(book.getBookId());
	            availabilityMap.put(book.getBookId(), isAvailable);
	        }
	        model.addAttribute("availabilityMap", availabilityMap);
		
		
		return "books/list";

	}
	
	

	
}
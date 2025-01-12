package com.nt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.APIResponse;
import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.service.CategoryService;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<APIResponse> create(@Valid @RequestBody CategoryRequestDTO dto) {

		Category addedCatogory = categoryService.createCategory(dto);
		if (addedCatogory != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new APIResponse(dto, "Category Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Some Error Occured"));

		}

	}

	@GetMapping("{id}")
	public ResponseEntity<APIResponse> getById(@PathVariable int id) throws CategoryNotFoundException {

		Category category = categoryService.getById(id);
		if (category != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new APIResponse(category, "Category Got Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Some Error Occured"));

		}

	}

	@PutMapping("{id}")
	public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody Category category) {

		Category updatedCatogory = categoryService.updateCategory(category, id);
		if (updatedCatogory != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new APIResponse(category, "Category Updated Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Some Error Occured"));

		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse> deleteById(@PathVariable int id) throws CategoryNotFoundException {

		Category deletedCategory = categoryService.deleteById(id);
		if (deletedCategory != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new APIResponse(deletedCategory, "Category Deleted Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Some Error Occured"));

		}

	}

	@GetMapping
	public ResponseEntity<APIResponse> getAllCategoriesByPagianation(@RequestParam int page, @RequestParam int size) {

		List<Category> list = categoryService.getCategoriesAccPageination(page, size);

		if (list != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new APIResponse(list, "Category According to Pagination got Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponse(null, "Some Error Occured"));
		}

	}
}

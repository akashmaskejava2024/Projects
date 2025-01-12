package com.nt.serviceimpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.repository.CategoryRepository;
import com.nt.service.CategoryService;

@SpringBootTest
class CategoryServiceImplTest {

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;

	@BeforeEach
	void setup() {
		Category testCategory = new Category(1, "shirting");

		Mockito.when(categoryRepository.findById(1)).thenReturn(Optional.of(testCategory));
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(testCategory);

		Category c2 = new Category(2, "suiting");
		List<Category> list = new ArrayList<>();
		list.add(testCategory);
		list.add(c2);
		Page<Category> pages = new PageImpl<>(list);

		Mockito.when(categoryRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pages);

	}

	@Test
	void testGetById() throws CategoryNotFoundException {

		Category category = categoryService.getById(1);

		assertEquals(category.getName(), "shirting");

	}

	@Test
	void testCreateCategory() {
		CategoryRequestDTO dto = new CategoryRequestDTO(0, "shirting");

		Category addedCategory = categoryService.createCategory(dto);
		assertEquals(addedCategory.getName(), "shirting");

	}

	@Test
	void testUpdateCategory() {
		Category category = new Category(1, "shirting");

		Category updatedCategory = categoryService.updateCategory(category, 1);
		assertEquals(updatedCategory.getName(), "shirting");
		assertEquals(1, updatedCategory.getId());
	}

	@Test
	void testDeleteById() throws CategoryNotFoundException {

		Category deletedCategory = categoryService.deleteById(1);
		assertEquals(deletedCategory.getName(), "shirting");

	}

	@Test
	void testGetCategoriesAccPageination() {
		Category testCategory = new Category(1, "shirting");
		Category c2 = new Category(2, "suiting");
		List<Category> list = new ArrayList<>();
		list.add(testCategory);
		list.add(c2);

		List<Category> resList = categoryService.getCategoriesAccPageination(2, 2);

		for (int i = 0; i < resList.size(); i++) {
			assertEquals(resList.get(i).getName(), list.get(i).getName());
		}

	}

}

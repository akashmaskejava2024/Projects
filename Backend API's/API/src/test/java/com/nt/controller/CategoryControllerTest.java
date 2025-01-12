package com.nt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.service.CategoryService;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@BeforeEach
	void setup() {

		Category category = new Category(1, "shirting");

		Mockito.when(categoryService.createCategory(Mockito.any(CategoryRequestDTO.class))).thenReturn(category);

	}

	@Test
	void testCreate() {

		try {
			mockMvc.perform(post("api/categories").contentType(MediaType.APPLICATION_JSON)
					.content("{\n" + "    \"name\":\"Shirting\"\n" + "}"))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testGetById() {

		try {
			mockMvc.perform(get("api/categories/1")).andExpect(MockMvcResultMatchers.status().isAccepted());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	void testUpdateById() {

		try {
			mockMvc.perform(put("api/categories/1").contentType(MediaType.APPLICATION_JSON)
					.content("{\n" + "    \"name\":\"Shirting\"\n" + "}"))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDeleteById() {
		try {
			mockMvc.perform(delete("api/categories/1")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testGetAllCategoriesByPagianation() {
		try {
			mockMvc.perform(get("api/categories")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

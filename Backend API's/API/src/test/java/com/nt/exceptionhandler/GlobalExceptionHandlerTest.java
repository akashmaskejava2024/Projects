package com.nt.exceptionhandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nt.controller.CategoryController;
import com.nt.controller.ProductController;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@WebMvcTest(ProductController.class)
class GlobalExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductController productController;
	
	@MockBean 
	private CategoryController categoryController;

	@MockBean
	private GlobalExceptionHandler globalExceptionHandler;

	@BeforeEach
	void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(productController,categoryController)
				.setControllerAdvice(globalExceptionHandler)
				.build();
		
		
	}

	@Test
	void testCategoryNotfoundExceptionHandler() throws CategoryNotFoundException {
		
		
		Mockito.when(categoryController.getById(Mockito.anyInt())).thenThrow(CategoryNotFoundException.class);

		try {
			mockMvc.perform(get("api/categories/1"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}

	@Test
	void testProductNotfoundExceptionHandler() throws ProductNotFoundException {
		Mockito.when(productController.getById(Mockito.anyInt())).thenThrow(ProductNotFoundException.class);
		try {
			mockMvc.perform(get("api/products/1"))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

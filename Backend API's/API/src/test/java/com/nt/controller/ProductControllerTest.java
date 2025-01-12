package com.nt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;
import com.nt.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@BeforeEach
	void setup() throws CategoryNotFoundException, ProductNotFoundException {
		Category category = new Category(1, "shirting");
		ProductResponseDTO resDto = new ProductResponseDTO(1, "shirt", 100, 50, category);

		Mockito.when(productService.createProduct(Mockito.any(ProductRequestdto.class))).thenReturn(resDto);
		Mockito.when(productService.getById(Mockito.anyInt())).thenReturn(resDto);
		Mockito.when(productService.updateProduct(Mockito.anyInt(), Mockito.any(ProductRequestdto.class)))
				.thenReturn(resDto);

		Mockito.when(productService.deleteById(Mockito.anyInt())).thenReturn(resDto);

		ProductResponseDTO dto = new ProductResponseDTO(2, "shirt1", 100, 50, category);
		List<ProductResponseDTO> dtoList = new ArrayList<>();
		dtoList.add(resDto);
		dtoList.add(dto);
		Mockito.when(productService.getAllProductsPageWise(Mockito.anyInt(), Mockito.anyInt())).thenReturn(dtoList);
	}

	@Test
	void testCraeteProduct() {

		try {
			mockMvc.perform(
					post("api/products").contentType(MediaType.APPLICATION_JSON)
							.content("{\n" + "    \"name\":\"shirt1\",\n" + "    \"quantity\":30,\n"
									+ "    \"price\":100,\n" + "    \"categoryid\":1\n" + "}"))
					.andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testGetById() {

		try {
			mockMvc.perform(get("api/products/1")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testUpdateById() {

		try {
			mockMvc.perform(
					put("api/products/1").contentType(MediaType.APPLICATION_JSON)
							.content("{\n" + "    \"name\":\"shirt1\",\n" + "    \"quantity\":30,\n"
									+ "    \"price\":100,\n" + "    \"categoryid\":1\n" + "}"))
					.andExpect(MockMvcResultMatchers.status().isAccepted());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testDeleteById() {

		try {
			mockMvc.perform(delete("api/products/1")).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testGetAllProducts() {

		try {
			mockMvc.perform(get("api/products?page=3&size=2")).andExpect(MockMvcResultMatchers.status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

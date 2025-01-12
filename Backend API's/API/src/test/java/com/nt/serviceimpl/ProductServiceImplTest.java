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

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.entity.Product;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;
import com.nt.repository.ProductRepository;
import com.nt.service.CategoryService;
import com.nt.service.ProductService;

@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private CategoryService categoryService;

	@BeforeEach
	void setup() throws CategoryNotFoundException {
		Category category = new Category(1, "shirting");
		Product testProduct = new Product(1, "shirt", 100, 50, category);

		Mockito.when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(testProduct));
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(testProduct);
		Mockito.when(categoryService.getById(Mockito.anyInt())).thenReturn(category);

		Product p2 = new Product(2, "pant", 100, 50, category);
		List<Product> list = new ArrayList<>();
		list.add(testProduct);
		list.add(p2);
		Page<Product> pages = new PageImpl<>(list);

		Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pages);

	}

	@Test
	void testCreateProduct() throws CategoryNotFoundException {
		ProductRequestdto dto = new ProductRequestdto(1, "shirt", 100, 50, 1);

		ProductResponseDTO addedProduct = productService.createProduct(dto);
		assertEquals(dto.getName(), addedProduct.getName());
		assertEquals(dto.getCategoryid(), addedProduct.getCategory().getId());
	}

	@Test
	void testGetById() throws ProductNotFoundException {

		int id = 1;
		ProductResponseDTO dto = productService.getById(id);
		assertEquals(dto.getName(), "shirt");
		assertEquals(dto.getCategory().getId(), 1);

	}

	@Test
	void testUpdateProduct() {

		ProductRequestdto dto = new ProductRequestdto(1, "shirt", 100, 50, 1);
		ProductResponseDTO resDto = productService.updateProduct(1, dto);

		assertEquals(dto.getName(), resDto.getName());
		assertEquals(dto.getCategoryid(), resDto.getCategory().getId());

	}

	@Test
	void testDeleteById() throws ProductNotFoundException {

		ProductResponseDTO dto = productService.deleteById(1);
		assertEquals(dto.getName(), "shirt");
	}

	@Test
	void testGetAllProductsPageWise() {
		Category category = new Category(1, "shirting");
		Product testProduct = new Product(1, "shirt", 100, 50, category);
		Product p2 = new Product(2, "pant", 100, 50, category);
		List<Product> list = new ArrayList<>();
		list.add(testProduct);
		list.add(p2);

		List<ProductResponseDTO> dtoList = productService.getAllProductsPageWise(2, 2);

		for (int i = 0; i < dtoList.size(); i++) {
			assertEquals(dtoList.get(i).getName(), list.get(i).getName());
			assertEquals(dtoList.get(i).getCategory().getName(), list.get(i).getCategory().getName());

		}

	}

}

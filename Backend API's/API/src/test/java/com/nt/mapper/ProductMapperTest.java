package com.nt.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.entity.Product;

@SpringBootTest
class ProductMapperTest {
	
	
	
	@Autowired
	private ProductMapper mapper;

	@Test
	void testToEntity() {
		
		ProductRequestdto dto = new ProductRequestdto(1, "shirting", 100, 50, 0);
		
		Product product = mapper.toEntity(dto);
		assertEquals(dto.getName(), product.getName());
		
		
	}

	@Test
	void testToResponseDto() {
		Category category = new Category(1, "shirting");
		Product product = new Product(1, "shirting", 100, 50,category );
		
		ProductResponseDTO dto = mapper.toResponseDto(product);
		assertEquals(dto.getCategory().getId(),product.getCategory().getId());
		
	}

}

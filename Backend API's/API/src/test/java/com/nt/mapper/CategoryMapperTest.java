package com.nt.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;

@SpringBootTest
class CategoryMapperTest {
	
	
	@Autowired
	private CategoryMapper mapper;
	
	

	@Test
	void testToEntity() {
		
		CategoryRequestDTO dto = new CategoryRequestDTO(1, "shirting");
		
		Category category = mapper.toEntity(dto);
		assertEquals(dto.getName(), category.getName());
		
		
	}

}

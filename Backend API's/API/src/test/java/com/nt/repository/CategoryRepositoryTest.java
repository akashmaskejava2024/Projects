package com.nt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.nt.entity.Category;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TestEntityManager entManager;

	@BeforeEach
	void setup() {

		Category category = new Category(1, "shirting");

		if (category.getId() != 0) {
			category = entManager.merge(category); // Reattach to session
		} else {
			entManager.persist(category); // New entity, safe to persist
		}
		entManager.clear();
		entManager.flush();

	}

	@Test
	void testExistsByName() {

		boolean isPresent = categoryRepository.existsByName("shirting");

		assertEquals(isPresent, true);

	}

}

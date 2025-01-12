package com.nt.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.mapper.CategoryMapper;
import com.nt.repository.CategoryRepository;
import com.nt.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Category createCategory(CategoryRequestDTO dto) {

		Category addedCategory = categoryRepository.save(categoryMapper.toEntity(dto));
		if (addedCategory != null) {
			return addedCategory;
		} else {
			return null;
		}

	}

	@Override
	public Category getById(int id) throws CategoryNotFoundException {

		Optional<Category> opt = categoryRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CategoryNotFoundException("Category with this Id is not present", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public Category updateCategory(Category category, int id) {

		category.setId(id);
		Category updatedCategory = categoryRepository.save(category);
		return updatedCategory;
	}

	@Override
	public Category deleteById(int id) throws CategoryNotFoundException {
		Optional<Category> opt = categoryRepository.findById(id);

		if (opt.isPresent()) {
			categoryRepository.deleteById(id);
			return opt.get();
		} else {
			throw new CategoryNotFoundException("Category not present with this Id to Delele", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public List<Category> getCategoriesAccPageination(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Category> pageList = categoryRepository.findAll(pageable);

		List<Category> list = new ArrayList<>();
		for (Category c : pageList) {
			list.add(c);

		}

		return list;
	}

}

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

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.entity.Product;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;
import com.nt.mapper.ProductMapper;
import com.nt.repository.ProductRepository;
import com.nt.service.CategoryService;
import com.nt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CategoryService categoryService;

	@Override
	public ProductResponseDTO createProduct(ProductRequestdto dto) throws CategoryNotFoundException {

		Category category = categoryService.getById(dto.getCategoryid());
		Product product = productMapper.toEntity(dto);
		product.setCategory(category);

		Product addedProduct = productRepository.save(product);

		return productMapper.toResponseDto(addedProduct);

	}

	@Override
	public ProductResponseDTO getById(int id) throws ProductNotFoundException {

		Optional<Product> opt = productRepository.findById(id);
		if (opt.isPresent()) {
			return productMapper.toResponseDto(opt.get());
		} else {
			throw new ProductNotFoundException("Product With This Id does not Exists", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ProductResponseDTO updateProduct(int id, ProductRequestdto dto) {

		dto.setId(id);
		Product updatedProduct = productRepository.save(productMapper.toEntity(dto));
		return productMapper.toResponseDto(updatedProduct);

	}

	@Override
	public ProductResponseDTO deleteById(int id) throws ProductNotFoundException {

		ProductResponseDTO dto = getById(id);

		productRepository.deleteById(id);

		return dto;

	}

	@Override
	public List<ProductResponseDTO> getAllProductsPageWise(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<Product> list = productRepository.findAll(pageable);

		List<ProductResponseDTO> listDto = new ArrayList<>();
		for (Product p : list) {
			listDto.add(productMapper.toResponseDto(p));
		}

		return listDto;

	}
}

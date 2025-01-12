package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@Service
public interface ProductService {

	ProductResponseDTO createProduct(ProductRequestdto dto) throws CategoryNotFoundException;

	ProductResponseDTO getById(int id) throws ProductNotFoundException;

	ProductResponseDTO updateProduct(int id, ProductRequestdto dto);

	ProductResponseDTO deleteById(int id) throws ProductNotFoundException;

	List<ProductResponseDTO> getAllProductsPageWise(int page, int size);

}

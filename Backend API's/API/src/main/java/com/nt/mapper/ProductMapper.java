package com.nt.mapper;

import org.springframework.stereotype.Component;

import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Product;

@Component
public class ProductMapper {

	public Product toEntity(ProductRequestdto dto) {
		return new Product(dto.getId(), dto.getName(), dto.getQuantity(), dto.getPrice(), null);
	}

	public ProductResponseDTO toResponseDto(Product addedProduct) {

		return new ProductResponseDTO(addedProduct.getId(), addedProduct.getName(), addedProduct.getQuantity(),
				addedProduct.getPrice(), addedProduct.getCategory());
	}

}

package com.nt.dto;

import com.nt.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductResponseDTO {

	private int id;
	private String name;
	private int quantity;
	private int price;
	private Category category;
	
}

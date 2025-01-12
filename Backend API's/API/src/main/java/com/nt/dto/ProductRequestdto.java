package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class ProductRequestdto {

	private int id;
	private String name;
	private int quantity;
	private int price;
	private int categoryid;

}

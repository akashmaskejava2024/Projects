package com.nt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.APIResponse;
import com.nt.dto.ProductRequestdto;
import com.nt.dto.ProductResponseDTO;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;
import com.nt.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<APIResponse> craeteProduct(@Valid @RequestBody ProductRequestdto dto) throws CategoryNotFoundException {
		
		ProductResponseDTO resDto = productService.createProduct(dto);
		 if(resDto != null) {
			  return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(resDto, "Product Created Successfully"));
		  } else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Some Error Occured"));
		  }
	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponse> getById(@PathVariable int id) throws ProductNotFoundException {
		
		ProductResponseDTO resDto = productService.getById(id);
		 if(resDto != null) {
			  return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(resDto, "Product Got Successfully"));
		  } else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Some Error Occured"));
		  }
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<APIResponse> updateById(@PathVariable int id, @RequestBody ProductRequestdto dto) {
		
         ProductResponseDTO resDto = productService.updateProduct(id, dto);
         if(resDto != null) {
			  return ResponseEntity.status(HttpStatus.ACCEPTED).body(new APIResponse(resDto, "Product Updated Successfully"));
		  } else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Some Error Occured"));
		  }
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse> deleteById(@PathVariable int id) throws ProductNotFoundException {
		
		ProductResponseDTO resDto = productService.deleteById(id);
		 if(resDto != null) {
			  return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(resDto, "Product Deleted Successfully"));
		  } else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Some Error Occured"));
		  }
	}
	
	
	@GetMapping
	public ResponseEntity<APIResponse> getAllProducts(@RequestParam int page, @RequestParam int size) {
		
	   List<ProductResponseDTO> allProducts =	productService.getAllProductsPageWise(page,size);
	  
	   if(allProducts != null) {
			  return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(allProducts, "Product According to Pagination got Successfully"));
		  } else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse(null, "Some Error Occured"));
		  }
	   
	   
	   
	}
}

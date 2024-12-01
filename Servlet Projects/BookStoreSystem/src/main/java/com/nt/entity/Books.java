package com.nt.entity;

public class Books {

	
	private int id;
	private String title;
	private String author;
	private int price;
	private int stock;
	private boolean isAddedToCart;
	
	
	

	
	
	public Books(int id, String title, String author, int price, int stock, Boolean isAddedToCart) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.isAddedToCart = isAddedToCart;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public int getId() {
		return id;
	}

	

	public boolean isAddedToCart() {
		return isAddedToCart;
	}

	public void setAddedToCart(boolean isAddedToCart) {
		this.isAddedToCart = isAddedToCart;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	

	
	
	
}

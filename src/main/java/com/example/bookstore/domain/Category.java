package com.example.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> books;

	// Constructors
	public Category() {

	}

	public Category(String name) {
		super();
		this.name = name;
	}

	// Getters
	public Long getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBooks() {
		return books;
	}

	// Setters
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}

}

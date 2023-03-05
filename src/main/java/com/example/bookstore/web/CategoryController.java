package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	// List of all categories
	@GetMapping("/categorylist")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist"; // categorylist.html
	}

	// Add new category form
	@GetMapping("/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory"; // addcategory.html
	}

	@PostMapping("/savecategory")
	public String saveCategory(Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist"; // categorylist.html
	}

}

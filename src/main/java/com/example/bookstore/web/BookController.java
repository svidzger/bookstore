package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/index")
	public String homePage(Model model) {
		String welcomeMsg = "Hello reading enthusiast!";
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "index"; // index.html
	}

	@GetMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; // booklist.html
	}
}

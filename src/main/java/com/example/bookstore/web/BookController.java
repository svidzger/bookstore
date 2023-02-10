package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;

	// Main page controller
	@GetMapping("/index")
	public String homePage(Model model) {
		String welcomeMsg = "Hello reading enthusiast!";
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "index"; // index.html
	}

	// Getting all books from database controller
	@GetMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; // booklist.html
	}

	// Add book form controller
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook"; // addbook.html
	}
	
	@PostMapping("/save")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id")Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
}



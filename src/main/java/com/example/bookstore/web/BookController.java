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

	// Main page
	@GetMapping("/index")
	public String homePage(Model model) {
		String welcomeMsg = "Hello reading enthusiast!";
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "index"; // index.html
	}

	// Getting all books from database
	@GetMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; // booklist.html
	}

	// Add book form
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook"; // addbook.html
	}
	
	// Save book
	@PostMapping("/save")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist"; // booklist.html
	}
	
	// Delete book
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id")Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist"; // booklist.html
	}
	
	// Edit book
	@GetMapping("/edit/{id}")
	public String showModBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editbook"; // editbook.html
	}
}



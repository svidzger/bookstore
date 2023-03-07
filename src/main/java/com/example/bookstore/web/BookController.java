package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository categoryRepository;

	// Main page
	@GetMapping("/index")
	public String homePage(Model model) {
		String welcomeMsg = "Welcome to the book store!";
		model.addAttribute("welcomeMsg", welcomeMsg);
		return "index"; // index.html
	}

	// RESTful service to get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}

	// RESTful service to get book by id
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}

	// Getting all books from database
	@GetMapping("/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist"; // booklist.html
	}

	// Add new book form
	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook"; // addbook.html
	}

	// Save book
	@PostMapping("/savebook")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:/booklist"; // booklist.html
	}

	// Delete book
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist"; // booklist.html
	}

	// Edit book
	@GetMapping("/edit/{id}")
	public String showModBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook"; // editbook.html
	}
}

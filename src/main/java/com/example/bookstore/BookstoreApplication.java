package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	// Logger
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Testing data always created when app starts
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {

			// Testing data for Category entity
			Category c1 = new Category("Biography");
			Category c2 = new Category("Horror");
			Category c3 = new Category("Science");
			log.info("added 3 categories");

			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);

			log.info("fetch all categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}

			// Testing data for Book entity
			Book b1 = new Book("Spare", "Prince Harry", "9780593593806", 2023, 28.59, c1);
			Book b2 = new Book("The Shining", "Stephen King", "9780450040184", 1980, 15.99, c2);
			Book b3 = new Book("Cosmos", "Carl Sagan", "9780593593806", 1983, 12.99, c3);
			log.info("added 3 books");

			brepository.save(b1);
			brepository.save(b2);
			brepository.save(b3);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

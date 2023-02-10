package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	// Logger
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Testing data always created when app starts
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			log.info("added 3 books");
			Book b1 = new Book("Spare", "Prince Harry", "9780593593806", 2023, 28.50);
			Book b2 = new Book("Atomic Habits", "James Clear", "9780735211292", 2018, 20.60);
			Book b3 = new Book("Cosmos", "Carl Sagan", "9780593593806", 1983, 12.90);

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}

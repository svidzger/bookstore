package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Test to add book
    @Test
    public void addBookTest() {
        Book book = new Book("test title", "test author", "test isbn", 1900, 20.99,
                categoryRepository.findByName("Science"));
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    // Test to get book
    @Test
    public void getBookTest() {
        Book book = bookRepository.findByTitle("Cosmos");
        assertThat(book.getAuthor()).isEqualTo("Carl Sagan");
    }

    // Test to delete book
    @Test
    public void deleteBookTest() {
        Book book = bookRepository.findByTitle("Cosmos");
        bookRepository.deleteById(book.getId());
        Book after = bookRepository.findByTitle("Cosmos");
        assertThat(after).isNull();
    }
}

package com.example.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    // Test to add category
    @Test
    public void addCategoryTest() {
        Category category = new Category("Fantasy");
        categoryRepository.save(category);
        assertThat(category.getCategoryId()).isNotNull();
    }

    // Test to get category
    @Test
    public void getBookTest() {
        Category category = categoryRepository.findByName("Science");
        assertThat(category.getName()).isEqualTo("Science");
    }

    // Test to delete category
    @Test
    public void deleteCategoryTest() {
        Category category = categoryRepository.findByName("Science");
        categoryRepository.deleteById(category.getCategoryId());
        Category after = categoryRepository.findByName("Science");
        assertThat(after).isNull();
    }
}

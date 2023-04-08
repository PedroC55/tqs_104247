package com.ua.containers;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.ua.containers.model.Book;
import com.ua.containers.repository.BookRepository;

@Testcontainers
@SpringBootTest
@SuppressWarnings("rawtypes")
public class BookTest {
    
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
        .withUsername("duke")
        .withPassword("password")
        .withDatabaseName("test");

  @Autowired private BookRepository bookRepository;

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  @Order(1)
  public void testCreateBook() {
      Book book = new Book();
      book.setName("X-Men");
      bookRepository.saveAndFlush(book);
  }

  @Test
  @Order(2)
  public void testListBooks() {
      List<Book> books = bookRepository.findAll();
      assertThat(books).hasSize(1).extracting(Book::getName).contains("X-Men");
  }

  @Test
  @Order(3)
  public void testUpdateBook() {
    List<Book> books = bookRepository.findAll();

    Book newbook = new Book();
    newbook.setName("Avengers");
    bookRepository.saveAndFlush(newbook);

    for (Book book : books){
        if (book.getName().equals("X-Men")){
            book.setName("Branca de Neve");
        }
        assertThat(book.getName()).isEqualTo("Branca de Neve");
        assertThat(books).hasSize(2).extracting(Book::getName).contains("Branca de Neve");
    }
  }

  @Test
  @Order(4)
  public void testDeleteBook() {
      bookRepository.deleteAll();
      assertThat(bookRepository.count()).isEqualTo(0);
  }

}
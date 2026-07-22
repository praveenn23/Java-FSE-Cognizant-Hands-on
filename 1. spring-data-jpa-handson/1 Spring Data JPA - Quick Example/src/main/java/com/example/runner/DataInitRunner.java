package com.example.runner;

import com.example.entity.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitRunner implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==============================================");
        System.out.println("  Spring Data JPA - DML Operations Demo");
        System.out.println("==============================================\n");

        System.out.println("--- 1. CREATE: Inserting books using save() ---");

        List<Book> books = Arrays.asList(
            new Book("Spring in Action", "Craig Walls", "978-1617294945", 45.99),
            new Book("Hibernate in Action", "Gavin King", "978-1932394153", 39.99),
            new Book("Java Persistence with Hibernate", "Christian Bauer", "978-1617290459", 55.00),
            new Book("Pro Spring 5", "Iuliana Cosmina", "978-1484228074", 49.99),
            new Book("Spring Boot in Action", "Craig Walls", "978-1617292545", 42.50)
        );

        List<Book> savedBooks = bookService.saveAllBooks(books);
        savedBooks.forEach(book -> System.out.println("  Saved: " + book));
        System.out.println("  Total books inserted: " + bookService.countBooks());

        System.out.println("\n--- 2. READ: Querying books ---");

        System.out.println("\n  2a. findById(1):");
        Optional<Book> bookById = bookService.findBookById(1L);
        bookById.ifPresent(b -> System.out.println("    Found: " + b));

        System.out.println("\n  2b. findAll():");
        List<Book> allBooks = bookService.findAllBooks();
        allBooks.forEach(b -> System.out.println("    " + b));

        System.out.println("\n  2c. findByAuthor('Craig Walls'):");
        List<Book> craigBooks = bookService.findBooksByAuthor("Craig Walls");
        craigBooks.forEach(b -> System.out.println("    " + b));

        System.out.println("\n  2d. searchBooksByTitle('Spring'):");
        List<Book> springBooks = bookService.searchBooksByTitle("Spring");
        springBooks.forEach(b -> System.out.println("    " + b));

        System.out.println("\n  2e. findBookByIsbn('978-1932394153'):");
        Book hibernateBook = bookService.findBookByIsbn("978-1932394153");
        System.out.println("    Found: " + hibernateBook);

        System.out.println("\n--- 3. UPDATE: Modifying a book using save() ---");

        Book updatedData = new Book("Spring in Action (5th Edition)", "Craig Walls", "978-1617294945", 49.99);
        Book updatedBook = bookService.updateBook(1L, updatedData);
        System.out.println("  Updated: " + updatedBook);

        System.out.println("\n--- 4. DELETE: Removing a book using deleteById() ---");

        System.out.println("  Before delete - Total books: " + bookService.countBooks());
        bookService.deleteBookById(5L);
        System.out.println("  After delete  - Total books: " + bookService.countBooks());

        System.out.println("\n  Remaining books:");
        bookService.findAllBooks().forEach(b -> System.out.println("    " + b));

        System.out.println("\n==============================================");
        System.out.println("  DML Operations Demo Complete!");
        System.out.println("  H2 Console: http://localhost:8080/h2-console");
        System.out.println("  JDBC URL: jdbc:h2:mem:librarydb");
        System.out.println("==============================================");
    }
}
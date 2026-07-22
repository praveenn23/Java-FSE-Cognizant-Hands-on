package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository has been injected via setter injection.");
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void displayBooks() {
        System.out.println("\n--- BookService: Requesting books from BookRepository ---");
        bookRepository.displayBooks();
        System.out.println("--- BookService: Done ---");
    }
}
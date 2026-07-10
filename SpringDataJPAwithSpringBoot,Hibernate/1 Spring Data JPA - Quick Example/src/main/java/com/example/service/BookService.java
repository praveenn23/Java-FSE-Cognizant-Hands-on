package com.example.service;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveAllBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> searchBooksByTitle(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());
            return bookRepository.save(book);
        }
        return null;
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public long countBooks() {
        return bookRepository.count();
    }
}
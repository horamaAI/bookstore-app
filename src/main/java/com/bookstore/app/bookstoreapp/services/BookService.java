package com.bookstore.app.bookstoreapp.services;

import com.bookstore.app.bookstoreapp.models.Book;
import com.bookstore.app.bookstoreapp.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    public Book registerBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = this.getBookById(id);
        BeanUtils.copyProperties(book, existingBook, "book_id");// 3rd argument is to ignore property "session_id" when copying, otherwise id will be updated with null
        return bookRepository.saveAndFlush(existingBook);
    }
}

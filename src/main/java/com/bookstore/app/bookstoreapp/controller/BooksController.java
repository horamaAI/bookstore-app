package com.bookstore.app.bookstoreapp.controller;

import com.bookstore.app.bookstoreapp.models.Book;
import com.bookstore.app.bookstoreapp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> bookList() {
        return bookService.getAllBooks();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // return 201
    public Book registerBook(@RequestBody final Book book) {
        return bookService.registerBook(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

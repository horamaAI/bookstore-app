package com.bookstore.app.bookstoreapp.controller;

import com.bookstore.app.bookstoreapp.dto.BookDto;
import com.bookstore.app.bookstoreapp.exceptions.BadRequestException;
import com.bookstore.app.bookstoreapp.models.Book;
import com.bookstore.app.bookstoreapp.services.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    private final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @GetMapping
    public ResponseEntity<List<BookDto>> bookList() {
        List<BookDto> bookDtos = bookService.getAllBooks();
        logger.info("Retrieved all books properly");
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping(value = "register")
    @ResponseStatus(HttpStatus.CREATED) // return 201
    public ResponseEntity<?> registerBook(@Valid @RequestBody final BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorsLog = bindingResult.getAllErrors()
                    .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            BadRequestException badRequestResponse = new BadRequestException(String.valueOf(errorsLog), HttpStatus.BAD_REQUEST);
            logger.warn("Bad request: failed to register book: '{}'", bookDto.getTitle(), badRequestResponse);
            return ResponseEntity.badRequest().body(badRequestResponse);
        }

        if (!bookService.bookHasValidIsbn13(bookDto.getIsbn13())) {
            BadRequestException badRequestResponse = new BadRequestException("ISBN13 must be a 13 long chain of digits");
            logger.warn("Bad request: ISBN must be a 10 or 13 long chain of digits; failed to register book: '{}'", bookDto.getTitle(), badRequestResponse);
            return ResponseEntity.badRequest().body(badRequestResponse);
        }

        Book book = new Book(bookDto.getTitle(), bookDto.getIsbn13(), bookDto.getNum_pages(), bookDto.getPublication_date(), bookDto.getPublisher(), bookDto.getLanguage());

        BookDto savedBookDto = bookService.registerBook(book);
        logger.info("Registered book titled: {}", bookDto.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}

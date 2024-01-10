package com.bookstore.app.bookstoreapp.services;

import com.bookstore.app.bookstoreapp.dto.BookDto;
import com.bookstore.app.bookstoreapp.models.Book;
import com.bookstore.app.bookstoreapp.models.Language;
import com.bookstore.app.bookstoreapp.models.Publisher;
import com.bookstore.app.bookstoreapp.repositories.BookRepository;
import com.bookstore.app.bookstoreapp.repositories.LanguageRepository;
import com.bookstore.app.bookstoreapp.repositories.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    public BookDto registerBook(Book book) {
        System.out.println("checking value of book: " + book.toString());
        System.out.println("checking value of language: " + book.getLanguage().toString());
        System.out.println("checking value of publisher: " + book.getPublisher().toString());
        Optional<Publisher> existPublisher = publisherRepository.findById(book.getPublisher().getPublisher_id());
        Optional<Language> existLanguage = languageRepository.findById(book.getLanguage().getLanguage_id());

        existLanguage.ifPresent(book::setLanguage);
        existPublisher.ifPresent(book::setPublisher);

        Book savedBook = bookRepository.saveAndFlush(book);
//        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteBook(Book book) {
        deleteBookById(book.getBook_id());
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = this.getBookById(id);
        BeanUtils.copyProperties(book, existingBook, "book_id");// 3rd argument is to ignore property "session_id" when copying, otherwise id will be updated with null
        System.out.println("check update retrieved: " + existingBook);
        System.out.println("check update former: " + book);
        return bookRepository.saveAndFlush(existingBook);
    }

    public boolean bookHasValidIsbn13(String isbn13) {
//        System.out.println("check valid isbn :" + isbn13.matches("(\\d{10}|\\d{13})"));
        return isbn13.matches("(\\d{10}|\\d{13})");
//        return false;
//        return isbn13.matches("[0-9]{10,13}");
    }
}

package com.bookstore.app.bookstoreapp.controller;


import com.bookstore.app.bookstoreapp.dto.BookDto;
import com.bookstore.app.bookstoreapp.exceptions.BadRequestException;
import com.bookstore.app.bookstoreapp.models.Book;
import com.bookstore.app.bookstoreapp.models.Language;
import com.bookstore.app.bookstoreapp.models.Publisher;
import com.bookstore.app.bookstoreapp.repositories.BookRepository;
import com.bookstore.app.bookstoreapp.services.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.StringWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Test books controller")
@AutoConfigureMockMvc
public class BooksControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getAllBooksTest() throws Exception {
        String jsonInputList = "[{\"book_id\": 1," +
                "\"title\": \"MY World's First Love\"," +
                "\"isbn13\": \"8987059752\"," +
                "\"num_pages\": 276," +
                "\"publication_date\": \"1996-08-31T22:00:00.000+00:00\"," +
                "\"publisher\": {" +
                "\"publisher_id\": 1010," +
                "\"publisher_name\": \"Ignatius Press\"}," +
                "\"language\": {" +
                "\"language_id\": 2," +
                "\"language_code\": \"en-US\"," +
                "\"language_name\": \"United States English\"" +
                "}}]";

        ObjectMapper jsonMapper = new ObjectMapper();
        List<BookDto> booksDto = jsonMapper.readValue(jsonInputList, new TypeReference<List<BookDto>>() {});

        when(bookService.getAllBooks()).thenReturn(booksDto);
        mockMvc.perform(get("/api/v1/books").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(booksDto))).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[*].num_pages").isNotEmpty());
    }

    @Test
    public void registerBookTest() throws Exception {
        BookDto bookDto = getBookDto();
        Book book = new Book(bookDto.getTitle(), bookDto.getIsbn13(), bookDto.getNum_pages(), bookDto.getPublication_date(), bookDto.getPublisher(), bookDto.getLanguage());

        when(bookService.registerBook(any(Book.class))).thenReturn(bookDto);
        when(bookService.bookHasValidIsbn13(book.getIsbn13())).thenReturn(true);

        mockMvc.perform(post("/api/v1/books/register").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDto))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("MY World's First Love")))
                .andExpect(jsonPath("$.isbn13", is("8917039152")));

    }

    @Test
    public void registerInvalidInputBookTest() throws Exception {
        BookDto bookDto = getBookDto();
        String buffer = getBookDto().getIsbn13();
        bookDto.setIsbn13("8917039152"); // 11 chars

        StringWriter writer = new StringWriter();

//        MvcResult res = mockMvc.perform(post("/api/v1/books/register").contentType(MediaType.APPLICATION_JSON)
        mockMvc.perform(post("/api/v1/books/register").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(bookDto)))
                .andExpect(status().isBadRequest())
//                .andReturn();
                .andExpect(jsonPath("$.message", is("ISBN13 must be a 13 long chain of digits")));
//                .andExpect((exc) -> assertTrue(exc instanceof BadRequestException));
//                .andExpect(result -> assertEquals(result.getResolvedException().getMessage(), "ISBN13 must be a 13 long chain of digits"));

/*
        Optional<BadRequestException> badRequestException = Optional.ofNullable((BadRequestException) res.getResolvedException());
        if (badRequestException.isEmpty()) {
            System.out.println("NOOOO");
        }
        badRequestException.ifPresent((exc)-> {
            System.out.println("To test or not to test that is the question");
            assertThat(exc, is(instanceOf(BadRequestException.class)));
        });
*/
    }

    private static BookDto getBookDto() {
        Publisher testPublisher = new Publisher("Test publisher");

        Language testLanguage = new Language("en-US", "United States English");

        BookDto bookDto = new BookDto() {{
            setTitle("MY World's First Love");
            setIsbn13("8917039152");
            setNum_pages(430L);
            setPublication_date(Date.valueOf(LocalDate.now().minusYears(11)));
            setPublisher(testPublisher);
            setLanguage(testLanguage);
        }};
        return bookDto;
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            return objectMapper.writeValueAsString(obj);
        }
        catch (Exception exc) {
            throw new RuntimeException(exc);
        }
    }
}

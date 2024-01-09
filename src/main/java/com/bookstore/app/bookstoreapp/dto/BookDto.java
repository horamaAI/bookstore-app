package com.bookstore.app.bookstoreapp.dto;

import com.bookstore.app.bookstoreapp.models.Language;
import com.bookstore.app.bookstoreapp.models.Publisher;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.util.Date;

public class BookDto {
    private Long book_id;

    private String title;

    private String isbn13;

    private String description;

    private Long num_pages;

    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date publication_date;

    private Publisher publisher;

    private Language language;

    public BookDto() {}

    public BookDto(Long id, String title, String isbn13, String description, Long num_pages, Date publication_date, Publisher publisher, Language language) {
        this.book_id = id;
        this.title = title;
        this.isbn13 = isbn13;
        this.description = description;
        this.num_pages = num_pages;
        this.publication_date = publication_date;
        this.publisher = publisher;
        this.language = language;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(Long num_pages) {
        this.num_pages = num_pages;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}

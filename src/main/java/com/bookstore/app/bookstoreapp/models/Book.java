package com.bookstore.app.bookstoreapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// @Getter @Setter @NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long book_id;

    private String title;

    private String isbn13;

    private String description;
    private Long num_pages;

    public Book() {}

    public Book(String title, String isbn13, String description, Long num_pages, Date publication_date, Publisher publisher, Language language) {
        this.title = title;
        this.isbn13 = isbn13;
        this.description = description;
        this.num_pages = num_pages;
        this.publication_date = publication_date;
        this.publisher = publisher;
        this.language = language;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publication_date;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToOne
    @JoinColumn(name = "language_id")
    private Language language;

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    @Override
    public String toString() {
        return new StringBuilder().append(getBook_id())
                .append(", ").append(getTitle())
                .append(", ").append(getIsbn13())
                .append(", ").append(getNum_pages()).toString();
    }

}

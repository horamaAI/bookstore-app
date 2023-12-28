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
    private Long book_id;

    private String title;

    private String isbn13;


    private Long num_pages;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publication_date;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private List<Publisher> publishers;

    @OneToMany
    @JoinColumn(name = "language_id")
    private List<Language> languages;

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

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
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

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getBook_id())
                .append(", ").append(getTitle())
                .append(", ").append(getIsbn13())
                .append(", ").append(getNum_pages()).toString();
    }

}

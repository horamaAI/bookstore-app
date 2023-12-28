package com.bookstore.app.bookstoreapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "publisher")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// @Getter @Setter @NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisher_id;

    private String publisher_name;

    // @ManyToOne
    // @JoinColumn(name = "book_id")
    // private Book book;


    public Long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Long publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }
}

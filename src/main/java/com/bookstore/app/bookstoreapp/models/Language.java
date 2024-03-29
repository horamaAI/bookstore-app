package com.bookstore.app.bookstoreapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "book_language")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// @Getter @Setter @NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long language_id;

    private String language_code;

    private String language_name;

    // @ManyToOne
    // @JoinColumn(name = "book_id")
    // private Book book;

    public Language() {}

    public Language(String language_code, String language_name) {
        this.language_code = language_code;
        this.language_name = language_name;
    }

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getLanguage_id())
                .append(", ").append(getLanguage_code())
                .append(", ").append(getLanguage_name()).toString();
    }
}

package com.bookstore.app.bookstoreapp.repositories;

import com.bookstore.app.bookstoreapp.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long> {
}

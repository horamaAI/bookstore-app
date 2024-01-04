package com.bookstore.app.bookstoreapp.repositories;

import com.bookstore.app.bookstoreapp.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository  extends JpaRepository<Publisher, Long> {
}

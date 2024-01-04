package com.bookstore.app.bookstoreapp.repositories;

import com.bookstore.app.bookstoreapp.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}

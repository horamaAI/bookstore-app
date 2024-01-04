package com.bookstore.app.bookstoreapp.services;

import com.bookstore.app.bookstoreapp.models.Language;
import com.bookstore.app.bookstoreapp.repositories.LanguageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(Long id) {
        return languageRepository.getReferenceById(id);
    }

    public Language registerLanguage(Language language) {
        System.out.println("check language to register:" + language.toString());
        return languageRepository.saveAndFlush(language);
    }

    public void deleteLanguageById(Long id) {
        languageRepository.deleteById(id);
    }

    public void deleteLanguage(Language language) {
        deleteLanguageById(language.getLanguage_id());
    }

    public Language updateLanguage(Long id, Language language) {
        Language existingLanguage = this.getLanguageById(id);
        BeanUtils.copyProperties(language, existingLanguage, "language_id");
        return languageRepository.saveAndFlush(existingLanguage);
    }
}

package com.bookstore.app.bookstoreapp.controller;


import com.bookstore.app.bookstoreapp.models.Language;
import com.bookstore.app.bookstoreapp.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/languages")
public class LanguagesController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<Language> languageList() {
//        return languageService.getAllLanguages();
        List<Language> languages = languageService.getAllLanguages();
        languages.forEach(
                language -> System.out.println(language)
        );
        return languages;
    }

    @GetMapping
    @RequestMapping("{id}")
    public Language getLanguageById(@PathVariable Long id) {
        return languageService.getLanguageById(id);
    }

    @PostMapping(value = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public Language registerLanguage(@RequestBody final Language language) {
        return languageService.registerLanguage(language);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Language updateLanguage(@PathVariable Long id, @RequestBody Language language) {
        return languageService.updateLanguage(id, language);
    }

    @DeleteMapping("{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguageById(id);
    }
}

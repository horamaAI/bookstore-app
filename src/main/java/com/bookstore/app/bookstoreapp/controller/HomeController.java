package com.bookstore.app.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

public class HomeController {
    @Value("${app.version}")
    private String appVersion;
    @GetMapping
    @RequestMapping("/")
    public Map getStatus() {
        return new HashMap<String, String>() {{ put("app-version", appVersion);}};
    }
}

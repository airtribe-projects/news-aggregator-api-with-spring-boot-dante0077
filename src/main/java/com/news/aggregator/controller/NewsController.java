package com.news.aggregator.controller;

import com.news.aggregator.model.Article;
import com.news.aggregator.model.User;
import com.news.aggregator.service.NewsService;
import com.news.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity<?> getNewsForUser(Authentication authentication) {
        String email = authentication.getName(); // Email from JWT token

        User user = userService.getUserByEmail(email);

        List<String> preferences = user.getPreferences();

        if (preferences == null || preferences.isEmpty()) {
            return ResponseEntity.badRequest().body("User has no preferences set.");
        }

        List<Article> articles = newsService.fetchNewsForPreferences(preferences);

        return ResponseEntity.ok(articles);
    }
}

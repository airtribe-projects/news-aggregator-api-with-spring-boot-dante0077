package com.news.aggregator.controller;

import com.news.aggregator.model.User;
import com.news.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<String>> getPreferences(Authentication authentication) {
        String email = authentication.getName(); // comes from JWT subject
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user.getPreferences());
    }

    @PutMapping
    public ResponseEntity<?> updatePreferences(
            Authentication authentication,
            @RequestBody List<String> newPreferences) {

        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        user.setPreferences(newPreferences);
        userService.saveUser(user); // persist the changes

        return ResponseEntity.ok("Preferences updated successfully.");
    }

}

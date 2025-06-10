package net.journal.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.journal.journalApp.cache.AppCache;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired 
    private AppCache appCache;

    @GetMapping("/all")
    public ResponseEntity<?> getAlluser(){
        try {
            List<UserEntity> users = userService.getAllUser();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            System.err.println("Error fetching all users: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving users: " + e.getMessage());
        }
    }

    @GetMapping("/clear-cache")
    public ResponseEntity<String> clearCache(){
        appCache.init();
        return ResponseEntity.status(200).body("Cache cleared");
    }
}

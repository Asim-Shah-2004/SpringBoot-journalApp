package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getUser(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            return ResponseEntity.status(404).body("User not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePassword(@RequestBody String password){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity updatedUser = userService.updateUserPassword(username, password);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return ResponseEntity.status(404).body("Failed to update: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity deletedUser = userService.deleteUser(username);
            return ResponseEntity.ok(deletedUser);
        } catch (RuntimeException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            return ResponseEntity.status(404).body("Failed to delete: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }

}
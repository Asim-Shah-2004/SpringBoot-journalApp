package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.UserService;

@RestController
@RequestMapping("/public")
public class PublicControllers {
    
    @Autowired
    private UserService userService;

   @GetMapping("/health")
   public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Alive");
   } 

   @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity){
        UserEntity created = userService.createUser(userEntity);
        return ResponseEntity.ok(created);
    }

}

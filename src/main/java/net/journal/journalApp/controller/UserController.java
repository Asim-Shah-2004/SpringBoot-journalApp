package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.services.UserService;
import java.util.List;



@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity){
        System.out.print(userEntity);
        UserEntity created = userService.createUser(userEntity);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAlluser(){
        List<UserEntity> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String username){
        UserEntity user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserEntity> updatePassword(@PathVariable String username,@RequestBody String password){
        UserEntity updatedUser = userService.updateUserPassword(username,password);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable String username){
        UserEntity deletedUser = userService.deleteUser(username);
        return ResponseEntity.ok(deletedUser);
    }

}

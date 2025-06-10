package net.journal.journalApp.services;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.journal.journalApp.repository.UserRepository;
import net.journal.journalApp.entity.UserEntity;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(UserEntity userEntity){
        Instant now = Instant.now();
        userEntity.setCreatedAt(now);
        userEntity.setUpdatedAt(now);
        String rawPassword = userEntity.getPassword();
        userEntity.setPassword(passwordEncoder.encode(rawPassword));
        userEntity.setRoles(Arrays.asList("USER"));
        return userRepository.save(userEntity);
    }

    public UserEntity getUser(String username){
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        return user;
    }
    
    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }
    

    public UserEntity updateUserPassword(String username, String newPassword) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    public UserEntity deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }
        userRepository.delete(user);
        return user;
    }
}

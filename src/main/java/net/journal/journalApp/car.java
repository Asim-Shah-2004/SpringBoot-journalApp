package net.journal.journalApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class car {
    
    @Autowired
    public Dog dog;

    @GetMapping("/honk")
    public void honk() {
        System.out.println("Beep! Beep!");
        dog.bark();
    }
}

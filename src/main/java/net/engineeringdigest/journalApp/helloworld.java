package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloworld {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello World!!";
    }
}

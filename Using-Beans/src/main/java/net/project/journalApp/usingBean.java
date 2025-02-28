package net.project.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class usingBean {

    @Autowired
    private bean bean;

    @GetMapping("/bean")
    public String print(){
        return bean.print();    
    }
}


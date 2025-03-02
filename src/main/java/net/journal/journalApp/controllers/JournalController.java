package net.journal.journalApp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import net.journal.journalApp.entities.Journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    
    private HashMap<Long, Journal> journalMap = new HashMap<>();

    @GetMapping
    public List<Journal> newJournal() {
        return new ArrayList<>(journalMap.values());
    }

    @PostMapping
    public boolean newJournal(@RequestBody Journal journal) {
        journalMap.put(journal.getId(), journal);
        return true;
    }

    @GetMapping("/{id}")
    public Journal getJournal(@PathVariable Long id) {
        return journalMap.get(id);
    }

    @PutMapping
    public boolean editJournal(@RequestBody Journal journal){
        if(journal.getId()==null) return false;
        journalMap.put(journal.getId(), journal);
        return true;
    }

    @DeleteMapping("/{id}")
    public boolean deleteJournal(@PathVariable Long id){
        if(id==null) return false;
        journalMap.remove(id);
        return true;
    }

}

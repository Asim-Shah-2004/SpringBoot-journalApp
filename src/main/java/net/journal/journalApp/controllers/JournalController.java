package net.journal.journalApp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import net.journal.journalApp.entities.Journal;
import net.journal.journalApp.services.JournalEntryService;

import java.util.Date;

@RestController
@RequestMapping("/journal")
public class JournalController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public Iterable<Journal> newJournal() {
        return journalEntryService.getAllJournalEntries();
    }

    @PostMapping
    public Journal newJournal(@RequestBody Journal journal) {
        journal.setDate(new Date());
        journalEntryService.saveJournalEntry(journal);
        return journal;
    }

    @GetMapping("/{id}")
    public Journal getJournal(@PathVariable String id) {
        return journalEntryService.getJournalEntryById(id);
    }

    @PutMapping
    public Journal editJournal(@RequestBody Journal journal){
        journalEntryService.editJournalEntry(journal.getId(), journal);
        return journal;
    }

    @DeleteMapping("/{id}")
    public boolean deleteJournal(@PathVariable String id){
        journalEntryService.deleteJournalEntry(id);
        return true;
    }

}

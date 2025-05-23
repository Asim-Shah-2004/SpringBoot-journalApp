package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import net.journal.journalApp.entity.JournalEntity;
import net.journal.journalApp.services.JournalEntryService;


import java.util.List;
import org.bson.types.ObjectId;
import javax.validation.Valid;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public ResponseEntity<JournalEntity> createJournalEntry(@Valid @RequestBody JournalEntity journalEntry) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        JournalEntity created = journalEntryService.createJournalEntry(journalEntry,username);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId id) {
        JournalEntity entry = journalEntryService.getJournalEntryById(id);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateJournalEntry(@PathVariable ObjectId id, @Valid @RequestBody JournalEntity journalEntry) {
        JournalEntity updated = journalEntryService.updateJournalEntry(id, journalEntry);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable ObjectId id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        journalEntryService.deleteJournalEntry(id,username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<JournalEntity>> getAllJournalEntries() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JournalEntity> entries = journalEntryService.getAllJournalEntries(username);
        return ResponseEntity.ok(entries);
    }
}
